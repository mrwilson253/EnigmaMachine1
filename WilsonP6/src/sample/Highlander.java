package sample;

import java.util.Random;

public class Highlander extends Enigma {

    private int key;
    private String codedMessage;

    public Highlander(){
        key = 1;
    }

    protected void encode(){

    }

    protected void decode(){

    }

    @Override
    public void setMessage(String m){
        super.setMessage(m);
        codedMessage = super.getCodedMessage();
        key = super.getKey();
        //TODO: encode(); //GEnigma encode()
    }

    @Override
    public void setMessage(String m, int k){
        super.setMessage(m,k);
        codedMessage = super.getCodedMessage();
        //TODO: encode(); //local
    }

    @Override
    public void setCodedMessage(String cm, int k){
        codedMessage = cm;
        key = k;
        //TODO: decode() //local
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
