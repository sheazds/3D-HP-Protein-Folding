/* $RCSfile$
 * $Author: hansonr $
 * $Date: 2006-12-11 13:29:38 -0600 (Mon, 11 Dec 2006) $
 * $Revision: 6442 $
 *
 * Copyright (C) 2004-2005  The Jmol Development Team
 *
 * Contact: jmol-developers@lists.sf.net
 *
 *  This library is free software; you can redistribute it and/or
 *  modify it under the terms of the GNU Lesser General Public
 *  License as published by the Free Software Foundation; either
 *  version 2.1 of the License, or (at your option) any later version.
 *
 *  This library is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 *  Lesser General Public License for more details.
 *
 *  You should have received a copy of the GNU Lesser General Public
 *  License along with this library; if not, write to the Free Software
 *  Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package org.jmol.modelsetbio;


import org.jmol.constant.EnumStructure;
import org.jmol.modelset.Atom;
import org.jmol.modelset.Bond;
import org.jmol.modelset.Chain;
import org.jmol.util.AxisAngle4f;
import org.jmol.util.Escape;
import org.jmol.util.Logger;
import org.jmol.util.Matrix3f;
import org.jmol.util.P3;
import org.jmol.util.Quaternion;
import org.jmol.util.TextFormat;
import org.jmol.util.V3;
import org.jmol.viewer.JC;

public class AminoMonomer extends AlphaMonomer {

  private final static byte CA = 0;
  private final static byte O = 1;
  private final static byte N = 2;
  private final static byte C = 3;
  private final static byte OT = 4;
//  private final static byte O1 = 5;
//  private final static byte SG = 6;
  
  // negative values are optional
  final static byte[] interestingAminoAtomIDs = {
    JC.ATOMID_ALPHA_CARBON,      // 0 CA alpha carbon
    ~JC.ATOMID_CARBONYL_OXYGEN,   // 1 O wing man
    JC.ATOMID_AMINO_NITROGEN,    // 2 N
    JC.ATOMID_CARBONYL_CARBON,   // 3 C  point man
    ~JC.ATOMID_TERMINATING_OXT,  // 4 OXT
//    ~JmolConstants.ATOMID_O1,               // 5 O1
//    ~JmolConstants.ATOMID_SG,               // 6 CYS SG
  };

  /**
   * @j2sIgnoreSuperConstructor
   * 
   */
  protected AminoMonomer() {
  }

  static Monomer validateAndAllocate(Chain chain, String group3, int seqcode,
                                     int firstAtomIndex, int lastAtomIndex,
                                     int[] specialAtomIndexes, Atom[] atoms) {
    byte[] offsets = scanForOffsets(firstAtomIndex, specialAtomIndexes,
        interestingAminoAtomIDs);
    if (offsets == null)
      return null;
    checkOptional(offsets, O, firstAtomIndex, specialAtomIndexes[JC.ATOMID_O1]);
    if (atoms[firstAtomIndex].isHetero()
        && !isBondedCorrectly(firstAtomIndex, offsets, atoms))
      return null;
    return new AminoMonomer().set2(chain, group3, seqcode, firstAtomIndex,
        lastAtomIndex, offsets);
  }

  private static boolean isBondedCorrectlyRange(int offset1, int offset2,
                                   int firstAtomIndex,
                                   byte[] offsets, Atom[] atoms) {
    int atomIndex1 = firstAtomIndex + (offsets[offset1] & 0xFF);
    int atomIndex2 = firstAtomIndex + (offsets[offset2] & 0xFF);
    // why would order matter here? True, it's usually N CA C O,
    // but it certainly doesn't have to be.
    //if (atomIndex1 >= atomIndex2)
      //return false;
    return (atomIndex1 != atomIndex2 && atoms[atomIndex1].isBonded(atoms[atomIndex2]));
  }

  private static boolean isBondedCorrectly(int firstAtomIndex, byte[] offsets,
                                 Atom[] atoms) {
    return (isBondedCorrectlyRange(N, CA, firstAtomIndex, offsets, atoms)
            && isBondedCorrectlyRange(CA, C, firstAtomIndex, offsets, atoms)
            && (!have(offsets, O) 
                || isBondedCorrectlyRange(C, O, firstAtomIndex, offsets, atoms))
            );
  }
  
  ////////////////////////////////////////////////////////////////

  boolean isAminoMonomer() { return true; }

  @Override
  public Atom getNitrogenAtom() {
    return getAtomFromOffsetIndex(N);
  }

  Atom getCarbonylCarbonAtom() {
    return getAtomFromOffsetIndex(C);
  }

  @Override
  public Atom getCarbonylOxygenAtom() {
    return getWingAtom();
  }

  @Override
  Atom getInitiatorAtom() {
    return getNitrogenAtom();
  }

  @Override
  Atom getTerminatorAtom() {
    // problem in JavaScript with byte == -1
    return getAtomFromOffsetIndex(have(offsets, OT) ? OT : C);
  }

  boolean hasOAtom() {
    return have(offsets, O);
  }
  
  ////////////////////////////////////////////////////////////////

  @Override
  boolean isConnectedAfter(Monomer possiblyPreviousMonomer) {
    if (possiblyPreviousMonomer == null)
      return true;
    AminoMonomer other = (AminoMonomer)possiblyPreviousMonomer;
    return other.getCarbonylCarbonAtom().isBonded(getNitrogenAtom());
  }

  ////////////////////////////////////////////////////////////////

  @Override
  void findNearestAtomIndex(int x, int y, Atom[] closest,
                            short madBegin, short madEnd) {
    
    Atom competitor = closest[0];
    Atom nitrogen = getNitrogenAtom();
    short marBegin = (short) (madBegin / 2);
    if (marBegin < 1200)
      marBegin = 1200;
    if (nitrogen.screenZ == 0)
      return;
    int radiusBegin = scaleToScreen(nitrogen.screenZ, marBegin);
    if (radiusBegin < 4)
      radiusBegin = 4;
    Atom ccarbon = getCarbonylCarbonAtom();
    short marEnd = (short) (madEnd / 2);
    if (marEnd < 1200)
      marEnd = 1200;
    int radiusEnd = scaleToScreen(nitrogen.screenZ, marEnd);
    if (radiusEnd < 4)
      radiusEnd = 4;
    Atom alpha = getLeadAtom();
    if (isCursorOnTopOf(alpha, x, y, (radiusBegin + radiusEnd) / 2,
        competitor)
        || isCursorOnTopOf(nitrogen, x, y, radiusBegin, competitor)
        || isCursorOnTopOf(ccarbon, x, y, radiusEnd, competitor))
      closest[0] = alpha;
  }

  boolean nhChecked = false;

  public void resetHydrogenPoint() {
    nhChecked = false;
    nitrogenHydrogenPoint = null;
  }

  P3 getNitrogenHydrogenPoint() {
    if (nitrogenHydrogenPoint == null && !nhChecked) {
      nhChecked = true;
      nitrogenHydrogenPoint = getExplicitNH();
    }
    return nitrogenHydrogenPoint;
  }
  
  P3 getExplicitNH() {
    Atom nitrogen = getNitrogenAtom();
    Atom h = null;
    Bond[] bonds = nitrogen.getBonds();
    if (bonds == null)
      return null;
    for (int i = 0; i < bonds.length; i++)
      if ((h = bonds[i].getOtherAtom(nitrogen)).getElementNumber() == 1)
        return h;
    return null;
  }

  public boolean getNHPoint(P3 aminoHydrogenPoint, V3 vNH,
                            boolean jmolHPoint, boolean dsspIgnoreHydrogens) {
    if (monomerIndex == 0 || groupID == JC.GROUPID_PROLINE)
      return false;
    Atom nitrogenPoint = getNitrogenAtom();
    P3 nhPoint = getNitrogenHydrogenPoint();
    if (nhPoint != null && !dsspIgnoreHydrogens) {
      vNH.sub2(nhPoint, nitrogenPoint);
      aminoHydrogenPoint.setT(nhPoint);
      return true;
    }
    AminoMonomer prev = (AminoMonomer) bioPolymer.monomers[monomerIndex - 1];
    if (jmolHPoint) {
      // Jmol: based on trigonal planar C-NH-Ca
      /* prior to Jmol 12.1.45 was not bisecting correctly 
      vNH.sub(nitrogenPoint, getLeadAtom());
      vNH.add(nitrogenPoint);
      vNH.sub(prev.getCarbonylCarbonAtom());
      */
      vNH.sub2(nitrogenPoint, getLeadAtom());
      vNH.normalize();
      V3 v = new V3();
      v.sub2(nitrogenPoint, prev.getCarbonylCarbonAtom());
      v.normalize();
      vNH.add(v);
    } else {
      // Rasmol def -- just use C=O vector, so this does not account for cis-amino acids
      // but I guess if those are just proline...
      P3 oxygen = prev.getCarbonylOxygenAtom();
      if (oxygen == null) // an optional atom for Jmol
        return false;
      vNH.sub2(prev.getCarbonylCarbonAtom(), oxygen);
    }
    vNH.normalize();
    aminoHydrogenPoint.add2(nitrogenPoint, vNH);
    nitrogenHydrogenPoint = P3.newP(aminoHydrogenPoint);
    if (Logger.debugging)
      Logger.info("draw ID \"pta" + monomerIndex + "_" + nitrogenPoint.index + "\" "
          + Escape.eP(nitrogenPoint) + Escape.eP(aminoHydrogenPoint)
          + " # " + nitrogenPoint);
    return true;
  }

  final private P3 ptTemp = new P3();
  final private static float beta = (float) (17 * Math.PI/180);
  
  @Override
  P3 getQuaternionFrameCenter(char qType) {
    switch (qType) {
    default:
    case 'a':
    case 'b':
    case 'c':
    case 'C':
      return getQuaternionFrameCenterAlpha(qType);
    case 'n':
      return getNitrogenAtom();
    case 'p':
    case 'P': // ramachandran
      return getCarbonylCarbonAtom();
    case 'q': // Quine -- center of peptide bond
      if (monomerIndex == bioPolymer.monomerCount - 1)
        return null;
      AminoMonomer mNext = ((AminoMonomer) bioPolymer.getGroups()[monomerIndex + 1]);
      P3 pt = P3.newP(getCarbonylCarbonAtom());
      pt.add(mNext.getNitrogenAtom());
      pt.scale(0.5f);
      return pt;
    }
  }

  @Override
  public Quaternion getQuaternion(char qType) {
    /*
     * also NucleicMonomer
     *  
     * see:
     * 
     *  Hanson and Thakur: http://www.cs.indiana.edu/~hanson/  http://www.cs.indiana.edu/~sithakur/
     *  
     *  Albrecht, Hart, Shaw, Dunker: 
     *  
     *   Contact Ribbons: a New Tool for Visualizing Intra- and Intermolecular Interactions in Proteins
     *   Electronic Proceedings for the 1996 Pacific Symposium on Biocomputing
     *   http://psb.stanford.edu/psb-online/proceedings/psb96/albrecht.pdfx
     *   
     *  Kneller and Calligari:
     *  
     *   Efficient characterization of protein secondary structure in terms of screw motion
     *   Acta Cryst. (2006). D62, 302-311    [ doi:10.1107/S0907444905042654 ]
     *   http://scripts.iucr.org/cgi-bin/paper?ol5289
     * 
     *  Wang and Zang:
     *   
     *   Protein secondary structure prediction with Bayesian learning method
     *   http://cat.inist.fr/?aModele=afficheN&cpsidt=15618506
     *
     *  Geetha:
     *  
     *   Distortions in protein helices
     *   International Journal of Biological Macromolecules, Volume 19, Number 2, August 1996 , pp. 81-89(9)
     *   http://www.ingentaconnect.com/content/els/01418130/1996/00000019/00000002/art01106
     *   DOI: 10.1016/0141-8130(96)01106-3
     *    
     *  Kavraki:
     *  
     *   Representing Proteins in Silico and Protein Forward Kinematics
     *   http://cnx.org/content/m11621/latest
     *   
     *  Quine: (an early paper on local helical paths)
     *  
     *  J. R. Quine, Journal of Molecular Structure: THEOCHEM, 
     *  Volume 460, Issues 1-3, 26 February 1999, pages 53-66
     *  
     */
    
    P3 ptC = getCarbonylCarbonAtom();
    P3 ptCa = getLeadAtom();
    V3 vA = new V3();
    V3 vB = new V3();
    V3 vC = null;
    
    switch (qType) {
    case 'a':
    case 'n':
      // amino nitrogen chemical shift tensor frame      
      // vA = ptH - ptN rotated beta (17 degrees) clockwise (-) around Y (perp to plane)
      // vB = ptCa - ptN
      if (monomerIndex == 0 || groupID == JC.GROUPID_PROLINE)
        return null;
      vC = new V3();
      getNHPoint(ptTemp, vC, true, false);
      vB.sub2(ptCa, getNitrogenAtom());
      vB.cross(vC, vB);
      Matrix3f mat = new Matrix3f();
      mat.setAA(AxisAngle4f.newVA(vB, -beta));
      mat.transform(vC);
      vA.cross(vB, vC);
      break;
    case 'b': // backbone
      return getQuaternionAlpha('b');
    case 'c':
      //vA = ptC - ptCa
      //vB = ptN - ptCa
      vA.sub2(ptC, ptCa);
      vB.sub2(getNitrogenAtom(), ptCa);
      break;
    case 'p':
    case 'x':
      //Bob's idea for a peptide plane frame
      //vA = ptCa - ptC
      //vB = ptN' - ptC
      if (monomerIndex == bioPolymer.monomerCount - 1)
        return null;
      vA.sub2(ptCa, ptC);
      vB.sub2(((AminoMonomer) bioPolymer.getGroups()[monomerIndex + 1]).getNitrogenAtom(), ptC);
      break;
    case 'q': 
      // J. R. Quine, Journal of Molecular Structure: THEOCHEM, 
      // Volume 460, Issues 1-3, 26 February 1999, pages 53-66
      //vA = ptCa - ptC
      //vB = ptCa' - ptN'
      if (monomerIndex == bioPolymer.monomerCount - 1)
        return null;
      AminoMonomer mNext = ((AminoMonomer) bioPolymer.getGroups()[monomerIndex + 1]);
      vB.sub2(mNext.getLeadAtom(), mNext.getNitrogenAtom());
      vA.sub2(ptCa, ptC);
      break;
    default:
      return null;
    }
    return Quaternion.getQuaternionFrameV(vA, vB, vC, false);
  }
  
  @Override
  public boolean isWithinStructure(EnumStructure type) {
    ProteinStructure s = (ProteinStructure) getStructure();
    return (s != null && s.isWithin(monomerIndex) && s.type == type);
  }
  
  @Override
  public String getStructureId() {
    if (proteinStructure == null || proteinStructure.structureID == null)
      return "";
    return proteinStructure.structureID;
  }
  
  @Override
  public String getProteinStructureTag() {
    if (proteinStructure == null || proteinStructure.structureID == null)
      return null;
    String tag = "%3N %3ID";
    tag = TextFormat.formatStringI(tag, "N", proteinStructure.serialID);
    tag = TextFormat.formatStringS(tag, "ID", proteinStructure.structureID);
    if (proteinStructure.type == EnumStructure.SHEET)
      tag += TextFormat.formatStringI("%2SC", "SC", proteinStructure.strandCount);
    return tag;
  }
  
}
