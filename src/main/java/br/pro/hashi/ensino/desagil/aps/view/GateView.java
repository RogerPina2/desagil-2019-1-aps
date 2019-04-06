package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GateView extends JPanel implements ActionListener {

    private final Gate gate;

    private final JCheckBox[] entradaField;
    private final JTextField saidaField;
    private final int[] pinos;
    private final Switch[] switches;

    public GateView(Gate gate) {
        this.gate = gate;

        JLabel saidaLabel = new JLabel("Saida:");
        saidaField = new JTextField();
        entradaField = new JCheckBox[this.gate.getInputSize()];
        pinos = new int[this.gate.getInputSize()];
        switches = new Switch[this.gate.getInputSize()];

        for (int x = 0; x < this.gate.getInputSize(); x++) {

            entradaField[x] = new JCheckBox("Input: " + x);
            switches[x] = new Switch();
            pinos[x] = x;
            gate.connect(x, switches[x]);
            add(entradaField[x]);
            entradaField[x].addActionListener(this);
        }

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        add(saidaLabel);
        add(saidaField);
        saidaField.setEnabled(false);

        update();
    }

    private void update() {
        boolean entrada;

        for(int numero: pinos){

            entrada = entradaField[numero].isSelected();

            if (entrada){
                switches[numero].turnOn();
            }else{
                switches[numero].turnOff();
            }
        }
        saidaField.setText(Boolean.toString(gate.read()));
    }

    // O que esta componente deve fazer quando o usuário der um
    // Enter depois de digitar? Apenas chamar o update, é claro!
    @Override
    public void actionPerformed(ActionEvent event) {
        update();
    }
}
