import java.io.FileWriter;
import java.io.IOException;

public class Consumer implements Runnable {
    Buffer B;
    String fileN;
    public Consumer(Buffer b, String s){
        this.B=b;
        this.fileN=s;
    }
    @Override
    public void run() {
        try{
            FileWriter fw=new FileWriter(fileN);
            while(!B.is_emp() || !B.produce_done){
                //as long as the buffer isnt empty or the producer isnt done keep looping
                String n=B.consume();
                // use the numbers an write it 
                fw.append(n+",");
                //Main.c++;
                //Main.mv = n;
            }
            //then close the file
            fw.close();

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }



}