import java.util.Random;

public class Randomizador
{
    
    private static Random rand = new Random(); //para poder dar reset foi preciso tirar o final
    

    public static Random getRandom()
    {
        
            return rand;
        
    }
    
    public static void reset()
    {
        
            rand = new Random();
        
    }
}
