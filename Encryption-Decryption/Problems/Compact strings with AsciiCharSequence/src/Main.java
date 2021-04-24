import java.util.*;

class AsciiCharSequence implements CharSequence {
    // implementation
    private byte[] array;

    public AsciiCharSequence(byte[] array) {
        this.array = array.clone();
    }

    @Override
    public int length() {
        return array.length;
    }

    @Override
    public char charAt(int index) {
        return (char) array[index];
    }

    @Override
    public AsciiCharSequence subSequence(int start, int end) {
        byte[] subSeq = new byte[end - start];
        System.arraycopy(array, start, subSeq, 0, end - start);
        return new AsciiCharSequence(subSeq);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (byte b : array) {
            sb.append((char) b);
        }
        return sb.toString();
    }
}