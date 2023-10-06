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
            
            String stringaRicevuta;

            //Decido il range di numeri 
            System.out.println("Inserisci 1 per generare un numero da 1 a 100, 2 da 1 a 300, 3 da 1 a 500");
            String rangeNumeri = inputTastiera.readLine();
            out.writeBytes(rangeNumeri + "\n");

            do{
                //Inserisco il numero
                System.out.println("Inserisci il numero da indovinare:");
                String numeroInserito = inputTastiera.readLine();

                //Invio risposta
                out.writeBytes(numeroInserito + "\n");

                //Leggo e stampo risposta
                stringaRicevuta = in.readLine();

                //Controlli risposta
                if(stringaRicevuta.equals("4")){
                    System.out.println("Inserire un numero valido!");
                }
                else if(stringaRicevuta.equals("1")){
                    System.out.println("Numero inserito troppo piccolo!");
                }
                else if(stringaRicevuta.equals("2")){
                    System.out.println("Numero inserito troppo grande!");
                }
            } while(!stringaRicevuta.equals("3"));

            System.out.println("HAI INDOVINATO in " + in.readLine() + " tentativi!");
            System.out.println("------------");
            System.out.println("Chiusura del client");

            //Chiudo la connessione
            s.close();

        } catch ( Exception e) {
            System.out.println(e.getMessage());
            System.out.println("Qualcosa è andato storto");
            System.exit(1);
        }
    }
}