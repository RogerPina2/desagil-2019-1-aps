package br.pro.hashi.ensino.desagil.aps.model;

public class AndGate extends Gate {
    private final NandGate nand0;
    private final NandGate nand1;

    public AndGate() {
        super(2);
        nand0 = new NandGate();
        nand1 = new NandGate();

        nand1.connect(0, nand0);
        nand1.connect(1, nand0);
    }

    @Override
    public boolean read() {
        return nand1.read();

    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1){
            throw new IndexOutOfBoundsException(inputPin);
        }
        if (inputPin == 0){
            nand0.connect(0, emitter);
        }
        else{
            nand0.connect(1, emitter);
        }
    }
}
