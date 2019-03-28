package br.pro.hashi.ensino.desagil.aps.model;

public class OrGate extends Gate {
    private final NandGate nand0;
    private final NandGate nand1;
    private final NandGate nand2;

    public OrGate() {
        super(2);
        nand0 = new NandGate();
        nand1 = new NandGate();
        nand2 = new NandGate();

        nand2.connect(0, nand0);
        nand2.connect(1, nand1);
    }

    @Override
    public boolean read() {
        return nand2.read();

    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1){
            throw new IndexOutOfBoundsException(inputPin);
        }
        if (inputPin == 0){
            nand0.connect(0, emitter);
            nand0.connect(1, emitter);
        }
        else{
            nand1.connect(0, emitter);
            nand1.connect(1, emitter);
        }
    }
}
