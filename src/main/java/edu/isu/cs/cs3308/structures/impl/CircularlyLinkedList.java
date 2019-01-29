package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;

public class CircularlyLinkedList<E> extends SinglyLinkedList<E>
{
    public E removeFirst()
    {
        if(isEmpty())
        {
            return null;
        }

        tail.setNext(head.getNext());
        E delValue = head.getValue();
        head = head.getNext();

        size--;
        return delValue;
    }

    public E removeLast()
    {
        if(isEmpty())
        {
            return null;
        }
        E delValue = tail.getValue();
        tail = head;

        for(int i = 1; i < size - 1; i++)
        {
            //tail how we keep track of our position
            tail = tail.getNext();
        }
        //the node after tail is the head
        tail.setNext(head);
        size--;
        return delValue;
    }

    public void addLast(E element)
    {
        if(element != null)
        {
            Node<E> nodeToAdd = new Node<E>(element, head);
            if(isEmpty() == false)
            {
                //if empty add to the end
                tail.setNext(nodeToAdd);
            }
            else
            {
                //if it is empty make the head point to itself to retain the loop
                head = nodeToAdd;
                nodeToAdd.setNext(head);
            }
            //set tail to new node
            tail = nodeToAdd;
            size++;
        }

    }
}
