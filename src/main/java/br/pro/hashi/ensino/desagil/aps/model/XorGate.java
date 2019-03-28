package br.pro.hashi.ensino.desagil.aps.model;

public class XorGate extends Gate {
    private final NandGate nand0;
    private final NandGate nand1;
    private final NandGate nand2;
    private final NandGate nand3;

    public XorGate() {
        super(2);
        nand0 = new NandGate();
        nand1 = new NandGate();
        nand2 = new NandGate();
        nand3 = new NandGate();

        nand3.connect(0, nand1);
        nand3.connect(1, nand2);

        nand1.connect(0,nand0);
        nand2.connect(0,nand0);
    }

    @Override
    public boolean read() {
        return nand3.read();

    }

    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        if (inputPin < 0 || inputPin > 1){
            throw new IndexOutOfBoundsException(inputPin);
        }
        if (inputPin == 0){
            nand0.connect(0, emitter);
            nand1.connect(1, emitter);
        }
        else{
            nand0.connect(1, emitter);
            nand2.connect(1, emitter);
        }
    }
}
