// ============================================================================
//  TrainerBag.java   -   CCE105 LABORATORY ACTIVITY 2 (SET 2)
//
//  A trainer's bag is a SINGLY LINKED LIST of item names.
//  Each name is stored in a Node. Each Node points to the next Node.
//  The class keeps only two things: head (the first Node) and count.
//
//      head -> [Potion] -> [Pokeball] -> [Antidote] -> null
//
//  There are 5 TODOs. Each one is 20 points.
//
//      TODO 1   DATA HIDING      make head and count private
//      TODO 2   CREATOR          constructor and emptyBag()
//      TODO 3   TRANSFORMER      pickUp() and use()
//      TODO 4   OBSERVER         isEmpty, isFull, size, find, first, last
//      TODO 5   INPUT / OUTPUT   display()
//
//  Every method is a loop that walks the list from head to the end:
//
//      Node current = head;
//      while (current != null) {
//          // use current.name here
//          current = current.next;
//      }
//
//  Do not edit anything marked FREEBIE.
//  Run RunCheck.java to check your work.
// ============================================================================

public class TrainerBag {

    /** FREEBIE - the maximum number of items in the bag. */
    public static final int MAX_BAG = 6;


    /** FREEBIE - one node of the list. Do not edit. */
    static class Node {
        String name;
        Node   next;

        Node(String name) {
            this.name = name;
            this.next = null;
        }
    }


    // ========================================================================
    //  TODO 1   (20 pts)   DATA HIDING
    // ========================================================================
    //  Put the word  private  in front of the two fields below.
    //  Do not rename them.
    // ========================================================================

   private Node head;      // first node in the list. null when the bag is empty
   private int  count;     // number of items in the bag


    // ========================================================================
    //  TODO 2   (20 pts)   CREATOR
    // ========================================================================
    //  The constructor must start with an empty bag.
    //  Code you can use:
    //
    //      head  = null;
    //      count = 0;
    //
    //  (or just call emptyBag(); after you finish it below)
    // ========================================================================

    public TrainerBag() {
   
    	emptyBag();

    }


    // ------------------------------------------------------------------------
    //  Still TODO 2: emptyBag() empties the bag.
    //  No loop needed. Code you can use:
    //
    //      head  = null;
    //      count = 0;
    // ------------------------------------------------------------------------

    public void emptyBag() {

        head = null;
        count = 0;

    }


    // ========================================================================
    //  TODO 3   (20 pts)   TRANSFORMER
    // ========================================================================
    //  pickUp(name) adds a new node at the END of the list.
    //
    //  Steps:
    //    1. If count == MAX_BAG, throw this:
    //         throw new IllegalStateException("The bag is full - drop something first");
    //    2. Create the node:
    //         Node fresh = new Node(name);
    //    3. If head == null, the list is empty, so:
    //         head = fresh;
    //    4. Otherwise walk to the last node and attach the new node to it:
    //         Node current = head;
    //         while (current.next != null) {
    //             current = current.next;
    //         }
    //         current.next = fresh;
    //    5. count++;
    //
    //  Note: step 4 uses  current.next != null  (stop ON the last node),
    //        not  current != null  (that goes one step too far).
    //
    //  ----------------------------------------------------------------------
    //  use(name) removes the node with that name. Returns true if
    //  removed, false if the name is not in the list.
    //
    //  Steps:
    //    1. If head == null, throw this:
    //         throw new IllegalStateException("The bag is empty - nothing to use");
    //    2. If the head is the one to remove:
    //         if (head.name.equals(name)) {
    //             head = head.next;
    //             count--;
    //             return true;
    //         }
    //    3. Otherwise walk with a pointer that stays ONE NODE BEFORE the
    //       one you are checking, and unlink when you find it:
    //         Node before = head;
    //         while (before.next != null) {
    //             if (before.next.name.equals(name)) {
    //                 before.next = before.next.next;
    //                 count--;
    //                 return true;
    //             }
    //             before = before.next;
    //         }
    //    4. If the loop ends, the name was not found:
    //         return false;
    //
    //  Note: compare names with  .equals(name)  , not  ==  .
    // ========================================================================

    public void pickUp(String name) {

    	if (count == MAX_BAG) {
    		throw new IllegalStateException("The bag is full - drop something first");
    	}
    	
    	Node fresh = new Node(name);
    	if (head == null) {
    		head = fresh;
    	}else {
    		Node current = head;
    		while (current.next != null) {
    			current = current.next;
    		}
    		current.next = fresh;
    	}
    	count++;

    }


    public boolean use(String name) {
    	if(head == null) {
    		throw new IllegalStateException("The bag is empty - nothing to use");
    	}
    	if(head.name.equals(name)) {
    		head = head.next;
    		count--;
    		return true;
    	}
    	
    	Node before = head;
        while (before.next != null) {
            if (before.next.name.equals(name)) {
                before.next = before.next.next;
                count--;
                return true;
            }
            before = before.next;
        }
        return false;
    }

    // ========================================================================
    //  TODO 4   (20 pts)   OBSERVER
    // ========================================================================
    //  These methods only read the list. They must not change head or count.
    //
    //  isEmpty()   return true if count is 0
    //  isFull()    return true if count is MAX_BAG
    //  size()      return count
    //
    //  find(name)  return the position of that name, starting at 1 for the
    //              head. Return -1 if the name is not in the list.
    //              Code you can use:
    //                  Node current  = head;
    //                  int  position = 1;
    //                  while (current != null) {
    //                      if (current.name.equals(name)) {
    //                          return position;
    //                      }
    //                      current = current.next;
    //                      position++;
    //                  }
    //                  return -1;
    //
    //  first()     return head.name
    //  last()      walk to the last node and return its name:
    //                  Node current = head;
    //                  while (current.next != null) {
    //                      current = current.next;
    //                  }
    //                  return current.name;
    //
    //  first() and last() must throw this if the bag is empty (head == null):
    //      throw new IllegalStateException("The bag is empty");
    // ========================================================================

    public boolean isEmpty() {

        return count == 0;

    }


    public boolean isFull() {

        return count == MAX_BAG;

    }


    public int size() {

        return count;

    }


    public int find(String name) {

    	Node current = head;
        int position = 1;

        while (current != null) {
            if (current.name.equals(name)) {
                return position;
            }
            current = current.next;
            position++;
        }

        return -1;
    }


    public String first() {

    	if (head == null) {
            throw new IllegalStateException("The bag is empty");
        }
        return head.name;
    }


    public String last() {

    	if (head == null) {
            throw new IllegalStateException("The bag is empty");
        }

        Node current = head;
        while (current.next != null) {
            current = current.next;
        }
        return current.name;
    }


    // ========================================================================
    //  TODO 5   (20 pts)   INPUT / OUTPUT
    // ========================================================================
    //  display() prints the whole list on ONE line.
    //
    //  If the bag is empty, print exactly this and return:
    //
    //      (the bag is empty)
    //
    //  Otherwise, for a bag holding Potion, Pokeball, Antidote, print:
    //
    //      BAG -> [Potion] -> [Pokeball] -> [Antidote] -> null   (3 in bag)
    //
    //  Code you can use:
    //
    //      System.out.print("BAG");
    //      Node current = head;
    //      while (current != null) {
    //          System.out.print(" -> [" + current.name + "]");
    //          current = current.next;
    //      }
    //      System.out.println(" -> null   (" + count + " in bag)");
    //
    //  Use  while (current != null)  for the loop, not a for loop on count.
    //  Only the last line uses println. Everything else uses print.
    // ========================================================================

    public void display() {
        if (head == null) {
            System.out.println("(the bag is empty)");
            return;
        }

        System.out.print("BAG");
        Node current = head;
        while (current != null) {
            System.out.print(" -> [" + current.name + "]");
            current = current.next;
        }
        System.out.println(" -> null   (" + count + " in bag)");
    }
}
