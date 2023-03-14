public class Producer implements Runnable {
    Buffer buff;
    long n;

    public Producer(Buffer buff, long n) {
        this.buff = buff;
        this.n = n;
    }

    @Override
    public void run() {
        for (int i = 2; i <= n; i++) {
            boolean prime = true;
            for (int j = 2; j <= Math.pow(i, 0.5); j++) {
                if (i % j == 0) {
                    prime = false;
                    break;
                }
            }
            if (prime) {
                //to check if buffer queue full(wait)or will add
                buff.produce(Integer.toString(i));
                //count number of prime numbers
                Main.c++;
                //get max number
                Main.mv = i;
            }
        }
        buff.done();
    }
}