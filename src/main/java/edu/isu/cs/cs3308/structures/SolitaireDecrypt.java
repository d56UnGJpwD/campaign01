package edu.isu.cs.cs3308.structures;

import edu.isu.cs.cs3308.structures.impl.CircularlyLinkedList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileInputStream;
import java.io.IOException;

public class SolitaireDecrypt
{

    private CircularlyLinkedList<Integer> deck = new CircularlyLinkedList<>();
    private CircularlyLinkedList<Integer> keystream = new CircularlyLinkedList<>();
    private CircularlyLinkedList<Integer> convertedToInt = new CircularlyLinkedList<>();

    private String decryptedM = "";
    private int indexJokerA;
    private int indexJokerB;


    public SolitaireDecrypt(String file)
    {
        convertToDeck(file);
    }
/*
    public String execute(String str)
    {
        convertToInt(str);

        while(keystream.size() != convertedToInt.size())
        {
            firstThing();
        }

        someFunc();

        return decryptedM;
    }
*/

    // This was used for IO https://www.tutorialspoint.com/java/io/inputstreamreader_read_char.htm
    private void convertToDeck(String filePath)
    {
        try {

            FileInputStream fis = new FileInputStream(filePath);
            FileReader fr = new FileReader(filePath);
            BufferedReader br = new BufferedReader(fr);

            String[] deckArr = br.readLine().split(" ");
            fis.close();

            int l = deckArr.length;
            int[] deckArray = new int[l];

            for (int i = 0; i < deckArr.length; i++)
            {
                deckArray[i] = Integer.parseInt(deckArr[i]);
            }

            // add contents to circular linked list
            for (int i = 0; i < deckArray.length; i++)
            {
                deck.addLast(deckArray[i]);
            }

        } catch (IOException ioe) {
            System.out.println("Read error");
        }

    }
}
