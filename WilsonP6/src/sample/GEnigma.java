package sample;

import java.util.Random;

public class GEnigma extends Enigma {

    private int key;
    private String codedMessage;
    private int newKey;
    private Random random;

    public GEnigma(){
        key = 1;
    }

    protected void encode(){
        //re-instantiate random = new Random(key);
        random = new Random(key);
        //make a new String
        String tempMessage = "";
        //create for loop that goes through codedMessage from 0 - length()-1
        for(int i = 0; i < codedMessage.length(); ++i){
            //for each character, create a new key from random
            newKey = random.nextInt(50);
            if(newKey==0) newKey=1;
            //Convert charAt(i) into a String
            String ch = Character.toString(codedMessage.charAt(i));
            //Check to see if i is even or odd
            if(i%2 == 0){ //even
                super.setMessage(ch, newKey);
                super.encode();
                tempMessage += super.getCodedMessage();
            }else {
                super.setMessage(ch, newKey);
                super.decode();
                tempMessage += super.getMessage();
            }
        }

        StringBuilder sb = new StringBuilder(tempMessage);
        sb.reverse();
        codedMessage = sb.toString();
    }

    protected void decode(){
        random = new Random(key);
        StringBuilder sb = new StringBuilder(codedMessage);
        sb.reverse();
        String forwardCoded = sb.toString();
        String tempMessage = "";

        for(int i = 0; i < forwardCoded.length(); ++i){
            newKey = random.nextInt(50);
            if(newKey==0) newKey=1;
            String ch = Character.toString(forwardCoded.charAt(i));
            if(i%2==0){
                super.setMessage(ch, newKey);
                super.decode();
                tempMessage += super.getMessage();
            }else{
                super.setMessage(ch, newKey);
                super.encode();
                tempMessage += super.getCodedMessage();
            }
        }

        codedMessage = tempMessage;
    }

    @Override
    public void setMessage(String m){
        super.setMessage(m);
        codedMessage = super.getCodedMessage();
        key = super.getKey();
        encode(); //GEnigma encode()
    }

    @Override
    public void setMessage(String m, int k){
        super.setMessage(m,k);
        codedMessage = super.getCodedMessage();
        encode(); //local
    }

    @Override
    public void setCodedMessage(String cm, int k){
        codedMessage = cm;
        key = k;
        decode(); //local
        super.setCodedMessage(codedMessage, key);
    }

    @Override
    public String getCodedMessage(){
        return codedMessage;
    }

    @Override
    public int getKey(){
        return key;
    }

}
