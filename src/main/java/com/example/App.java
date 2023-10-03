package com.example;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class App 
{
    public static void main( String[] args )
    {
        try{
            
            
            //Per input tastiera
            BufferedReader inputTastiera = new BufferedReader(new InputStreamReader(System.in));

            //Creo un socket
            Socket s = new Socket("localhost", 3000);

            //Creo i "tubi"
            DataOutputStream out = new DataOutputStream(s.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            boolean indovinato = false;
            do{
                //Inserisco il numero
                System.out.println("Inserisci il numero da indovinare");
                String stringaInserita = inputTastiera.readLine();

                //Invio risposta
                out.writeBytes(stringaInserita + "\n");

                //Leggo e stampo risposta
                String stringaRicevuta = in.readLine();

                //Controlli risposta
                if(stringaRicevuta.equals("1")){
                    System.out.println("Numero inserito troppo piccolo!");
                }
                else if(stringaInserita.equals("2")){
                    System.out.println("Numero inserito troppo grande!");
                }
                else if(stringaRicevuta.equals("3")){
                    System.out.println("HAI INDOVINATO in " + in.readLine() + " tentativi!");
                    indovinato = true;
                }
            } while(indovinato);
            
            //Chiudo la connessione
            s.close();

        } catch ( Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Qualcosa e' andato storto");
            System.exit(1);
        }
    }
}