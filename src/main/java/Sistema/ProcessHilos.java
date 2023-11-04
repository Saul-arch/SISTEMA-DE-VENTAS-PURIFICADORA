package Sistema;

public class ProcessHilos extends Thread{

    Thread hilo1;


    public void run(){

        for (int i = 0; i < 5; i++){
            System.out.println("Contando");

            try{
                Thread.currentThread();
                Thread.sleep(500);
            }catch (Exception e){
                System.out.println(e);
            }
        }

    }

}
