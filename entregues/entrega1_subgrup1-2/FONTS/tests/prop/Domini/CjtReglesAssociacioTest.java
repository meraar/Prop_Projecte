package prop.Domini;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

public class CjtReglesAssociacioTest {
	private CjtReglesAssociacio CRA;

	private static ArrayList<ReglaAssociacio> LlistaRegles1() throws Exception {
		ArrayList<ReglaAssociacio> listReg = new ArrayList<ReglaAssociacio>();
		listReg.add(new ReglaAssociacio(0, 0.5f, 0.1f,
				new AtributProcessat("malaltia","cert"), new HashSet<>(Arrays.asList(
						new AtributProcessat("temperatura", "alta"),
						new AtributProcessat("esport", "false")
				))));
		listReg.add(new ReglaAssociacio(1, 0.4f, 0.15f,
				new AtributProcessat("producte","cervesa"), new HashSet<>(Arrays.asList(
						new AtributProcessat("producte", "patates"),
						new AtributProcessat("producte", "olives")
				))));
		listReg.add(new ReglaAssociacio(2, 0.3f, 0.2f,
				new AtributProcessat("producte","aigua"), new HashSet<>(Arrays.asList(
				new AtributProcessat("producte", "xocolata"),
				new AtributProcessat("producte", "olives")
		))));
		return listReg;
	}
	private static ArrayList<ReglaAssociacio> LlistaRegles3() throws Exception {
		ArrayList<ReglaAssociacio> listReg = new ArrayList<ReglaAssociacio>();
		listReg.add(new ReglaAssociacio(1, 0.4f, 0.15f,
				new AtributProcessat("producte","cervesa"), new HashSet<>(Arrays.asList(
				new AtributProcessat("producte", "patates"),
				new AtributProcessat("producte", "olives")
		))));
		listReg.add(new ReglaAssociacio(2, 0.3f, 0.2f,
				new AtributProcessat("producte","aigua"), new HashSet<>(Arrays.asList(
				new AtributProcessat("producte", "xocolata"),
				new AtributProcessat("producte", "olives")
		))));
		return listReg;
	}

	private static ReglaAssociacio ReglaUpdate() throws Exception {
		ReglaAssociacio regla = new ReglaAssociacio();
		regla = new ReglaAssociacio(6,0.5f,0.3f,
				new AtributProcessat("cinèfil","cert"), new HashSet<>(Arrays.asList(
						new AtributProcessat("llibres", "interessat"),
						new AtributProcessat("escolta_música", "cert")
				)));
		return regla;
	}

	@Test
	public void getRegla() throws Exception {
		ArrayList<ReglaAssociacio> listReg = LlistaRegles1();

		CRA = new CjtReglesAssociacio(listReg);

		assertEquals(CRA.getRegla(1),listReg.get(1));
	}

	@Test
	public void getLlistRegAss() throws Exception {
		ArrayList<ReglaAssociacio> listReg = LlistaRegles1();

		CRA = new CjtReglesAssociacio(listReg);

		assertEquals(CRA.getLlistRegAss(),listReg);
	}

	@Test
	public void setLlistRegAss() throws Exception {
		CRA = new CjtReglesAssociacio();
		ArrayList<ReglaAssociacio> listReg = LlistaRegles1();

		CRA.setLlistRegAss(listReg);

		assertEquals(CRA.getLlistRegAss(),listReg);
	}

	@Test
	public void updateRegla() throws Exception {
       ArrayList<ReglaAssociacio> listReg1 = LlistaRegles1();

        CRA = new CjtReglesAssociacio(listReg1);
        ReglaAssociacio novaRegla = ReglaUpdate();

        CRA.updateRegla(0, novaRegla);
		ReglaAssociacio regla = new ReglaAssociacio();
        boolean excep;
		try {
			regla = CRA.getRegla(novaRegla.getId_regla()); // Es comprova que existeixi la nova regla associacio
			
		} catch (Exception e) {
		}
		assertEquals(novaRegla,regla);
	}

	@Test
	public void deleteRegla() throws Exception {
		ArrayList<ReglaAssociacio> listReg1 = LlistaRegles1();
		CRA = new CjtReglesAssociacio(listReg1);
		CRA.deleteRegla(0);
		ArrayList<ReglaAssociacio>  listReg3 = LlistaRegles3();
		boolean excep;
		try {
			CRA.getRegla(0); // Es comprova que no existeix la regla associacio eliminada
			excep = false;
		} catch (Exception e) {
			excep = true;
		}
		assertTrue(excep); // Si s ha eliminat correctament excep sera igual a true.
	}

}
