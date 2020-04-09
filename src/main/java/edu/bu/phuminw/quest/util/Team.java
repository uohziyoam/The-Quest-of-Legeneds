/**
 * Author: Phumin Walaipatchara
 */

package edu.bu.phuminw.quest.util;

import java.util.ListIterator;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * A team class holding any object as a team
 * @param <T> Object to hold
 */

public class Team<T> implements Iterable<T> {
    private int id;
    private String name;
    private ArrayList<T> members;
    private int maxMember;

    public Team(int id, int maxMember) {
        this.id = id;
        members = new ArrayList<T>(maxMember);
        this.maxMember = maxMember;
    }

    public void add(T member) {
        if (members.size() < maxMember)
            members.add(member);
    }

    /**
     * Interactively add members to the team
     */

    // public void addMembers() {
    //     StdinWrapper stdinwrap = new StdinWrapper("Enter team name: ");
    //     Random rand = new Random();
    //     boolean success = false;
    //     String token = "";
    //     System.out.printf("Team %d\n", id);

    //     while (!success) {
    //         token = stdinwrap.next();
    //         if (token == null) { // Special command
    //             if (stdinwrap.isQuit()) {
    //                 System.out.println("Goodbye...");System.exit(0);
    //             }
    //         }
    //         else if (token.length() > 0) {
    //             this.name = token; success = true;
    //         }
    //         else {
    //             success = false;
    //         }
    //     }

    //     boolean end = false;
    //     stdinwrap.setMessage("Enter name: ");
    //     while (members.size() < maxMember && !end) {
    //         success = false;
                
    //         while (!success && !end) {
    //             System.out.printf("Team %d member %d\n", name, members.size()+1);
    //             token = stdinwrap.next();
    //             if (token == null) { // Special command
    //                 if (stdinwrap.isQuit()) {
    //                     System.out.println("Goodbye...");System.exit(0);
    //                 }
    //                 else if (stdinwrap.isEnd()) {
    //                     end = true;
    //                 }
    //             }
    //             else if (token.length() > 0) {
    //                 members.add(new T(rand.nextInt(), token, this));
    //                 success = true;
    //             }
    //             else {
    //                 success = false;
    //             }
    //         }
    //     }

    //     if (members.size() == maxMember) {
    //         System.out.printf("Team %s is full...\n", name);
    //     }

    //     System.out.printf("\nTeam %s has the following member(s): ", name);
    //     ListIterator<T> li = members.listIterator();
    //     while (li.hasNext())
    //         System.out.printf("%s ", li.next().getName());
    //     System.out.printf("\n");
    // }

    public boolean removeMember(T p) {
        T pl;

        if ((pl = validT(p)) != null) {
            members.remove(pl);
            return true;
        }

        return false;
    }

    public boolean removeMembers(ArrayList<T> Ts) {
        ListIterator<T> li = Ts.listIterator();
        ArrayList<T> removed = new ArrayList<T>();
        boolean success = true;
        
        while (li.hasNext()) {
            T p = li.next();

            if (!members.remove(p)) {
                success = false;
                break;
            }

            removed.add(p);
        }

        // Add back after failure
        if (!success) {
            ListIterator<T> li1 = removed.listIterator();

            while (li1.hasNext()) {
                T p = li1.next();
                members.add(p);
            }
        }

        return success;
    }

    public int getSize() {
        return members.size();
    }

    public String getTeamName() {
        return name;
    }

    public int getId() {
        return id;
    }

    /**
     * Return a list of members
     * 
     * @return A list of members
     */

    public List<T> getMember() {
        List<T> toRet = new ArrayList<T>();

        for (T item: members)
            toRet.add(item);

        return toRet;
    }

    public T getAt(int i) {
        return members.get(i);
    }

    /**
     * Shuffle members order
     * 
     * @return List of shuffled result
     */

    public List<T> getRandomMembers() {
        Collections.shuffle(members);
        return members;
    }

    /**
     * Draw a random member from a team
     * 
     * @return Random member
     */

    public T randomMember() {
        Random rand = new Random();

        return members.get(rand.nextInt(members.size()));
    }

    public T validT(T p) {
        ListIterator<T> li = members.listIterator();
        
        while (li.hasNext()) {
            T pl = li.next();

            if (pl.equals(p)) {
                return pl;
            }
        }
        
        return null;
    }

    public Iterator<T> iterator() {
        return members.iterator();
    }

    @Override
    public String toString() {
        try {
            return "Team " + name + " of " + members.get(0).getClass().getSimpleName() + " with " + members.size() + " members";
        } catch (IndexOutOfBoundsException e) {
            return "Team " + name + " with " + members.size() + " members";
        }
    }
}
