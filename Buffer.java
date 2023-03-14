import java.util.LinkedList;
import java.util.Queue;
public class Buffer {

     private final Queue<String> queue =new LinkedList<>();
        private final int sizee;
        Boolean produce_done= false;
        public Buffer(int s){
            this.sizee=s;

        }
        public boolean is_emp() {
            return queue.size()==0;

        }
        public void done() {
            this.produce_done=true;
        }
        public synchronized void produce(String i){
            //if buff full then wait
            while(queue.size()==sizee){
                try{
                    wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            queue.add(i);
            notify();
        }
        public synchronized String consume() {
            //if buff empty then wait
            while(queue.isEmpty()){
                try{
                    wait();
                } catch(InterruptedException e){
                    e.printStackTrace();
                }
            }
            String i=queue.remove();
            notify();
            return i;

        }

    }


