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

    public String execute(String str)
    {
        //convertToInt(str);

        while(keystream.size() != convertedToInt.size())
        {
            beforeTCut();
        }

        //someFunc();

        return decryptedM;
    }


    // This was used for IO https://www.baeldung.com/java-read-file
    // https://www.geeksforgeeks.org/different-ways-reading-text-file-java/
    private void convertToDeck(String filePath)
    {
        try {
            //code required to read the file
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
//I dont have ascii memorized so I used this http://www.asciitable.com/

    private void intConverter(String str)
    {
        char[] charArr = str.toCharArray();

        for (int i = 0; i < charArr.length; i++)
        {
            //uppercase checker
            if (charArr[i] >= 65 && charArr[i] <= 90)
            {
                charArr[i] -= 64;
                convertedToInt.addLast((int) charArr[i]);
            }
            //lowercase checker
            else if (charArr[i] >= 97 && charArr[i] <= 122)
            {
                charArr[i] -= 96;
                convertedToInt.addLast((int) charArr[i]);
            }
        }
        //if not a multiple of 5, then pad it out with X's
        for (int i = 0; i < convertedToInt.size() % 5; i++) {
            convertedToInt.addLast(24);
        }
    }

    private void beforeTCut()
    {
        indexJokerA = deck.indexOf(27);
        indexJokerB = deck.indexOf(28);

        if (deck.get(indexJokerA) == deck.last())
        {
            //popping them out to move swap their positions
            int first = deck.removeFirst();
            int last = deck.removeLast();

            deck.addFirst(last);
            deck.addLast(first);
        } else if (indexJokerA == 26)
        {
            int firstJoker = deck.remove(indexJokerA);
            //int last = deck.removeLast();
            //deck.addLast(last);
            deck.addLast(firstJoker);
        } else
        {
            int jokerV = deck.remove(indexJokerA);
            int nextV = deck.remove(indexJokerA);

            deck.insert(nextV, indexJokerA);
            //shift over 1 position
            indexJokerA++;
            deck.insert(jokerV, indexJokerA);
        }

        //jokers values are 27 and 28 respectively and we can use the indexOf function to find the jokers index
        // so we can shuffle them
        indexJokerA = deck.indexOf(27);
        indexJokerB = deck.indexOf(28);

        //start moving second joker
        int secondJk = deck.remove(indexJokerB);


        //same check as with jokerA
        if (indexJokerB == 26)
        {
            int head = deck.removeFirst();
            deck.addFirst(secondJk);
            deck.addLast(head);

        }
        //easy move if he is in position 27
        else if (indexJokerB == 27)
        {
            int front = deck.removeFirst();
            deck.insert(secondJk, 1);
            deck.addLast(front);


        }
        else if (indexJokerB == 0)
        {
            //gets the first 2 of the deck
            int first = deck.removeFirst();
            int second = deck.removeFirst();
            //adds the joker after the second card
            deck.addFirst(secondJk);
            deck.addFirst(second);
            deck.addFirst(first);
        }
        else
        {
            deck.insert(secondJk, indexJokerB + 2);
        }

        indexJokerA = deck.indexOf(27);
        indexJokerB = deck.indexOf(28);

        tripleCut();
    }


    private void tripleCut()
    {

    }


}
