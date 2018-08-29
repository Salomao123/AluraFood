package br.com.alura.alurafood.validator;

import android.support.design.widget.TextInputLayout;
import android.widget.EditText;

import br.com.alura.alurafood.formatter.FormataTelefoneComDdd;

public class ValidaTelefoneComDDD implements Validador{

    public static final String DEVE_TER_DEZ_OU_ONZE_DIGITOS = "Telefone deve ter 10 a 11 digitos!";
    private final TextInputLayout textInputTelefoneComDDD;
    private final EditText campoTelefoneComDDD;
    private final ValidacaoPadrao validacaoPadrao;
    private final FormataTelefoneComDdd formatador = new FormataTelefoneComDdd();

    public ValidaTelefoneComDDD(TextInputLayout telefoneComDDD) {
        this.textInputTelefoneComDDD = telefoneComDDD;
        this.validacaoPadrao = new ValidacaoPadrao(telefoneComDDD);
        this.campoTelefoneComDDD = textInputTelefoneComDDD.getEditText();
    }


    private boolean validaEntreDezOuOnzeDigitos(String telefoneComDDD) {
        int digitos = telefoneComDDD.length();
        if (digitos < 10 || digitos > 11) {
            textInputTelefoneComDDD.setError(DEVE_TER_DEZ_OU_ONZE_DIGITOS);
            return false;
        }
        return true;
    }

    @Override
    public boolean estaValido() {
        if (!validacaoPadrao.estaValido()) return false;
        String telefoneComDDD = campoTelefoneComDDD.getText().toString();
        String telefoneComDddSemFormatacao = formatador.remove(telefoneComDDD);
        if (!validaEntreDezOuOnzeDigitos(telefoneComDddSemFormatacao)) return false;
        adicionaFormatacao(telefoneComDddSemFormatacao);
        return true;
    }

    private void adicionaFormatacao(String telefoneComDDD) {
        String telefoneComDddFormatado = formatador.formata(telefoneComDDD);
        campoTelefoneComDDD.setText(telefoneComDddFormatado);
    }


}
