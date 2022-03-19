package Drivers;

import prop.CtrlPresentacio;

import java.util.Scanner;

/**
 * Stub utilitzat al driver pel control de presentació
 */
public class CtrlPresentacioStub extends CtrlPresentacio {
    protected Scanner scanner;

    public CtrlPresentacioStub(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    protected String llegeixString(String text) {
        System.out.println(text + " ");
        return scanner.nextLine();
    }

    @Override
    protected int llegeixInt(String text) {
        String valor = llegeixString(text);

        try {
            return Integer.parseInt(valor);
        }
        catch (NumberFormatException e) {
            return llegeixInt(text);
        }
    }

    @Override
    protected boolean llegeixBool(String text) {
        String valor = llegeixString(text);

        if (valor.equals("true")) {
            return true;
        }
        else if (valor.equals("false")) {
            return false;
        }

        return llegeixBool(text);
    }

    @Override
    protected float llegeixFloat(String text) {
        String valor = llegeixString(text);

        try {
            return Float.parseFloat(valor);
        }
        catch (NumberFormatException e) {
            return llegeixFloat(text);
        }
    }
}
