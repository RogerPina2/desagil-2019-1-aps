package br.pro.hashi.ensino.desagil.aps.view;

import br.pro.hashi.ensino.desagil.aps.model.Gate;
import br.pro.hashi.ensino.desagil.aps.model.Light;
import br.pro.hashi.ensino.desagil.aps.model.Switch;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.net.URL;

//AND: https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/AND_ANSI.svg/1280px-AND_ANSI.svg.png
//NAND: https://upload.wikimedia.org/wikipedia/commons/thumb/f/f2/NAND_ANSI.svg/1280px-NAND_ANSI.svg.png
//XOR: https://upload.wikimedia.org/wikipedia/commons/thumb/0/01/XOR_ANSI.svg/1280px-XOR_ANSI.svg.png
//NOT: https://upload.wikimedia.org/wikipedia/commons/thumb/b/bc/NOT_ANSI.svg/1280px-NOT_ANSI.svg.png
//OR: https://upload.wikimedia.org/wikipedia/commons/thumb/b/b5/OR_ANSI.svg/1280px-OR_ANSI.svg.png
public class GateView extends FixedPanel implements ItemListener, MouseListener{
    private final Switch[] switches;
    private final Gate gate;

    private final JCheckBox[] inputBoxes;

    private final Light light;
    private final Image image;
    private Color color;

    public GateView(Gate gate) {
        super(210, 147);
        this.gate = gate;

        int inputSize = gate.getInputSize();

        switches = new Switch[inputSize];
        inputBoxes = new JCheckBox[inputSize];
        light = new Light();

        light.connect(0, gate);
        light.setR(0);
        light.setG(0);
        light.setB(255);

        for (int i = 0; i < inputSize; i++) {
            switches[i] = new Switch();
            inputBoxes[i] = new JCheckBox();

            gate.connect(i, switches[i]);

            if (inputSize != 1){
                add(inputBoxes[i],5,35 + i * 60,15,15);
            } else {
                add(inputBoxes[i], 5, 62, 15,15);
            }
        }

        for (JCheckBox inputBox : inputBoxes) {
            inputBox.addItemListener(this);
        }

        String name = gate.toString() + ".png";
        URL url = getClass().getClassLoader().getResource(name);
        image = getToolkit().getImage(url);

        addMouseListener(this);

        update();
    }

    private void update() {
        for (int i = 0; i < gate.getInputSize(); i++) {
            if (inputBoxes[i].isSelected()) {
                switches[i].turnOn();
            } else {
                switches[i].turnOff();
            }
        }

        int r = light.getR();
        int g = light.getG();
        int b = light.getB();

        color = new Color(r,g,b);
        repaint();

    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        // Descobre em qual posição o clique ocorreu.
        int x = e.getX();
        int y = e.getY();
        Color color;

        // Se o clique foi dentro do circulo colorido...
        if (Math.pow(x - (171 + 8.5), 2) + Math.pow(y - (63 + 8.5), 2) < Math.pow(8.5, 2)) {

            // ...então abrimos a janela seletora de cor...
            color = JColorChooser.showDialog(this, null, new Color(light.getR(), light.getG(), light.getB()));

            light.setR(color.getRed());
            light.setB(color.getBlue());
            light.setG(color.getGreen());

            // ...e chamamos repaint para atualizar a tela.

            repaint();
        }
    }
    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void paintComponent(Graphics g) {

        // Não podemos esquecer desta linha, pois não somos os
        // únicos responsáveis por desenhar o painel, como era
        // o caso nos Desafios. Agora é preciso desenhar também
        // componentes internas, e isso é feito pela superclasse.
        super.paintComponent(g);

        // Desenha a imagem, passando sua posição e seu tamanho.
        g.drawImage(image, 10, 20, 190, 109, this);

        // Desenha um quadrado cheio.
        g.setColor(color);
        g.fillOval(171, 63, 16, 18);

        // Linha necessária para evitar atrasos
        // de renderização em sistemas Linux.
        getToolkit().sync();
    }
}
