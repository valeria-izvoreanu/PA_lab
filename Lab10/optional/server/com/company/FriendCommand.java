package com.company;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FriendCommand extends Command {
    private PrintWriter out;
    public List<String> people;
    public Map<String, LinkedList<String>> friendLists;

    public FriendCommand(PrintWriter out, List<String> people, Map<String, LinkedList<String>> friendLists) {
        this.out = out;
        this.people = people;
        this.friendLists = friendLists;
    }

    private List<String> addFriend(String name, String friends) {
        if (friends == null) {
            return new LinkedList<>();
        }
        String[] friendList = friends.split(" ");
        LinkedList<String> newFriendList = new LinkedList<>();
        List<String> invalidFriends = new ArrayList<>();
        LinkedList<String> dummyFriend = new LinkedList<>();
        dummyFriend.add(name);
        for (String newFriend : friendList) {//add friends to every existent users and vice-versa
            if (people.contains(newFriend)) {
                newFriendList.add(newFriend);
                if (friendLists.containsKey(newFriend)) {
                    friendLists.get(newFriend).add(name);
                } else {
                    friendLists.put(newFriend, dummyFriend);
                }
            } else {
                invalidFriends.add(newFriend);
            }
        }
        if (newFriendList.size() > 0) {
            if (friendLists.containsKey(name)) {
                friendLists.get(name).addAll(newFriendList);
            } else {
                friendLists.put(name, newFriendList);
            }
        }
        return invalidFriends;//return people that haven't been registered
    }

    public void execute(String name, String friends) {
        List<String> invalidFriends = addFriend(name, friends);
        if (invalidFriends.size() > 0) {
            out.println("These people don't exist");
            for (String badFriend : invalidFriends) {
                out.println(badFriend);
            }
        } else {
            out.println("Friends added successfully");
        }
    }

    public Map<String, LinkedList<String>> getFriendLists() {
        return friendLists;
    }
}
