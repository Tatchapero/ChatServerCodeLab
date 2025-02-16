package org.example.util;

import java.util.ArrayList;
import java.util.List;

public class Filter {
    private static final List<String> bannedWords;

    private Filter() {}

    static {
        bannedWords = new ArrayList<>();
                bannedWords.add("anal");
                bannedWords.add("anus");
                bannedWords.add("arse");
                bannedWords.add("ass");
                bannedWords.add("ballsack");
                bannedWords.add("balls");
                bannedWords.add("bastard");
                bannedWords.add("bitch");
                bannedWords.add("biatch");
                bannedWords.add("bloody");
                bannedWords.add("blowjob");
                bannedWords.add("blow job");
                bannedWords.add("bollock");
                bannedWords.add("bollok");
                bannedWords.add("boner");
                bannedWords.add("boob");
                bannedWords.add("bugger");
                bannedWords.add("bum");
                bannedWords.add("butt");
                bannedWords.add("buttplug");
                bannedWords.add("clitoris");
                bannedWords.add("cock");
                bannedWords.add("coon");
                bannedWords.add("crap");
                bannedWords.add("cunt");
                bannedWords.add("damn");
                bannedWords.add("dick");
                bannedWords.add("dildo");
                bannedWords.add("dyke");
                bannedWords.add("fag");
                bannedWords.add("feck");
                bannedWords.add("fellate");
                bannedWords.add("fellatio");
                bannedWords.add("felching");
                bannedWords.add("fuck");
                bannedWords.add("f u c k");
                bannedWords.add("fudgepacker");
                bannedWords.add("fudge packer");
                bannedWords.add("flange");
                bannedWords.add("Goddamn");
                bannedWords.add("God damn");
                bannedWords.add("hell");
                bannedWords.add("homo");
                bannedWords.add("jerk");
                bannedWords.add("jizz");
                bannedWords.add("knobend");
                bannedWords.add("knob end");
                bannedWords.add("labia");
                bannedWords.add("lmao");
                bannedWords.add("lmfao");
                bannedWords.add("muff");
                bannedWords.add("nigger");
                bannedWords.add("nigga");
                bannedWords.add("omg");
                bannedWords.add("penis");
                bannedWords.add("piss");
                bannedWords.add("poop");
                bannedWords.add("prick");
                bannedWords.add("pube");
                bannedWords.add("pussy");
                bannedWords.add("queer");
                bannedWords.add("scrotum");
                bannedWords.add("sex");
                bannedWords.add("shit");
                bannedWords.add("s hit");
                bannedWords.add("sh1t");
                bannedWords.add("slut");
                bannedWords.add("smegma");
                bannedWords.add("spunk");
                bannedWords.add("tit");
                bannedWords.add("tosser");
                bannedWords.add("turd");
                bannedWords.add("twat");
                bannedWords.add("vagina");
                bannedWords.add("wank");
                bannedWords.add("whore");
                bannedWords.add("wtf");

        // SOURCE: http://www.bannedwordlist.com/lists/swearWords.txt
    }

    public static String message(String message) {
        for (String bannedWord : bannedWords) {
            message = message.replace(bannedWord, filteredWord(bannedWord));
        }

        return message;
    }

    public static void addBannedWord(String word) {
        Filter.bannedWords.add(word);
    }

    public static void removeBannedWord(String word) {
        Filter.bannedWords.remove(word);
    }

    public static List<String> getBannedWords() {
        return Filter.bannedWords;
    }

    public static boolean hasBannedWord(String message) {
        for (String bannedWord : bannedWords) {
            if (message.contains(bannedWord))
                return true;
        }

        return false;
    }

    private static String filteredWord(String word) {
        return "*".repeat(word.length());
    }
}
