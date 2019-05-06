package br.pro.hashi.ensino.desagil.aps.model;

public class HalfAdder extends Gate {
    private final NandGate nandLeft;
    private final NandGate nandTop;
    private final NandGate nandRight;
    private final NandGate nandBottom;
    private final NandGate nandBottomBottom;

    public HalfAdder() {
        super("Half-Adder", 2, 2);

        nandLeft = new NandGate();

        nandTop = new NandGate();
        nandTop.connect(1, nandLeft);

        nandBottom = new NandGate();
        nandBottom.connect(0, nandLeft);

        nandRight = new NandGate();
        nandRight.connect(0, nandTop);
        nandRight.connect(1, nandBottom);

        nandBottomBottom = new NandGate();
        nandBottomBottom.connect(0, nandLeft);
        nandBottomBottom.connect(1, nandLeft);
    }

    @Override
    public boolean read(int outputPin) {
        switch (outputPin) {
            case 0:
                return nandRight.read();
            case 1:
                return nandBottomBottom.read();
        }
        return false;
    }


    @Override
    public void connect(int inputPin, SignalEmitter emitter) {
        switch (inputPin) {
            case 0:
                nandTop.connect(0, emitter);
                nandLeft.connect(0, emitter);
                break;
            case 1:
                nandLeft.connect(1, emitter);
                nandBottom.connect(1, emitter);
                break;
            default:
                throw new IndexOutOfBoundsException(inputPin);
        }
    }
}
