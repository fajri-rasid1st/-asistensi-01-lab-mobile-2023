package com.example.assignment6;

import java.util.ArrayList;

public class DataSource {

    // buat array list dgn nama modelnya // buat variabel yg menyimpan data
    public static ArrayList<ModelContact> contacts = generateDummyModelContact();

    //buatkan arraynya
    private static ArrayList<ModelContact> generateDummyModelContact() {
        ArrayList<ModelContact> contacts = new ArrayList<>();
        contacts.add(new ModelContact("Hatsune Miku", "https://i.pinimg.com/564x/66/27/04/66270491b12ccc63ab146279ab3be27c.jpg", "+62 81234567890", "01", "January 12, 2029", chats().get(0)));
        contacts.add(new ModelContact("Kagamine Rin", "https://i.pinimg.com/originals/ab/cf/b7/abcfb72177e92038cd5ae8e7c932e3a5.jpg", "+62 81234567123", "Hooman Being", "June 1, 2019", chats().get(1)));
        contacts.add(new ModelContact("Kaito Shion", "https://i.pinimg.com/originals/61/26/7b/61267bbb9d3cc0cd2c2282b89d1cc1ea.jpg", "+62 81234567890", "Ngeeeeeng", "January 12, 2029", chats().get(2)));
        contacts.add(new ModelContact("Kagamine Len", "https://i.pinimg.com/originals/10/a5/b2/10a5b2c122047942b4ed9566ef15ace5.jpg", "+62 81234567890", "I'm Handsome", "January 12, 2029", chats().get(3)));
        contacts.add(new ModelContact("Megurine Luka", "https://i.pinimg.com/564x/de/17/a5/de17a5adb79db424b1a2368b1b11fac7.jpg", "+62 81234567890", "Still alive", "January 12, 2029", chats().get(4)));
        contacts.add(new ModelContact("Gumi", "https://i.pinimg.com/564x/ab/3c/f5/ab3cf5bf0a6224be8183958268a3939a.jpg", "+62 81234567890", "Let'sssss GOOOOOOO", "January 12, 2029", chats().get(5)));
        contacts.add(new ModelContact("Sakine Meiko", "https://i.pinimg.com/564x/f4/7f/dc/f47fdc15de18659a03919b1334114cbd.jpg", "+62 81234567890", "Still alive", "January 12, 2029", chats().get(6)));
        contacts.add(new ModelContact("IA", "https://i.pinimg.com/564x/ee/e7/7f/eee77f29165ff0e64b12cd32af6f067b.jpg", "+62 81234567890", "ARIA ON THE PLANETES", "January 12, 2029", chats().get(7)));
        contacts.add(new ModelContact("Hakuna", "https://i.pinimg.com/564x/be/c9/b3/bec9b3c28bc71408e662cb0d2e730ff0.jpg", "+62 81234567890", "Hi !", "January 12, 2029", chats().get(8)));

        return contacts;
    }

    //for chats
    public static ArrayList<ArrayList<ModelChat>> chats() {
        ArrayList<ArrayList<ModelChat>> conversation = new ArrayList<>();

        ArrayList<ModelChat> contact1 = new ArrayList<>();
        contact1.add(new ModelChat("Lorem Ipsum Dolor Sit Amet", "20.20"));
        contact1.add(new ModelChat("World Is Mine !!!! aaaaaaaaaaaaaaaaaaaaaa", "21.20"));
        contact1.add(new ModelChat("asa made odoru yume dake misete", "23.20"));
        contact1.add(new ModelChat("Tokei no kane ga toku mahou", "01.20"));
        contact1.add(new ModelChat("Aimaina yubi sasou kaidan", "02.20"));
        contact1.add(new ModelChat("Lorem Ipsum Dolor Sit Amet", "03.20"));
        contact1.add(new ModelChat("World Is Mine !!!! aaaaaaaaaaaaaaaaaaaaaa", "04.20"));
        contact1.add(new ModelChat("Lorem Ipsum Dolor Sit Amet", "05.20"));
        contact1.add(new ModelChat("World Is Mine !!!! aaaaaaaaaaaaaaaaaaaaaa", "06.20"));
        contact1.add(new ModelChat("Arifureta koigokoro ni  ima wana wo shikakeru Wazukana sukima nozokeba", "07.20"));
        contact1.add(new ModelChat("Tsukamaete", "08.20"));
        contact1.add(new ModelChat("Tatoeba fukai shigemi no naka suberikomasete Tsunaida ase no kaori ni  tada okasareteru", "09.20"));

        ArrayList<ModelChat> contact2 = new ArrayList<>();
        contact2.add(new ModelChat("劣等上等 BRING IT ON", "20.20"));
        contact2.add(new ModelChat("列島上々 不眠日本", "21.20"));
        contact2.add(new ModelChat("子供騙しのマセマティカ バレてんだってそんなのって プライドがないや", "23.20"));
        contact2.add(new ModelChat("ご立派 警鐘 気取りで", "01.20"));
        contact2.add(new ModelChat("高みの見物ばかりじゃつまんねぇ ほらもっと 間違って上等", "02.20"));
        contact2.add(new ModelChat("退屈に手をかけて", "03.20"));
        contact2.add(new ModelChat("ママ、やっぱあたしは", "04.20"));
        contact2.add(new ModelChat("こんなところじゃ終われない", "05.20"));

        ArrayList<ModelChat> contact3 = new ArrayList<>();
        contact3.add(new ModelChat("You were trembling inside the horse-carriage. Now tear away that wretched old outfit, and return to tonight's dance ball", "20.20"));
        contact3.add(new ModelChat("Hai'", "21.20"));
        contact3.add(new ModelChat("In the ashes, the glass slipper melts with a crimson glow", "23.20"));
        contact3.add(new ModelChat("I kiss your tears held in my hand, as an impulse runs through your back in that instant", "01.20"));
        contact3.add(new ModelChat("A princess who wears gunpowder smoke as perfume", "02.20"));
        contact3.add(new ModelChat("your unyielding pupil cleaves through my frigid mask", "03.20"));

        ArrayList<ModelChat> contact4 = new ArrayList<>();
        contact4.add(new ModelChat("劣等上等 BRING IT ON", "20.20"));
        contact4.add(new ModelChat("列島上々 不眠日本", "21.20"));
        contact4.add(new ModelChat("子供騙しのマセマティカ バレてんだってそんなのって プライドがないや", "23.20"));
        contact4.add(new ModelChat("ご立派 警鐘 気取りで", "01.20"));
        contact4.add(new ModelChat("高みの見物ばかりじゃつまんねぇ ほらもっと 間違って上等", "02.20"));
        contact4.add(new ModelChat("退屈に手をかけて", "03.20"));
        contact4.add(new ModelChat("ママ、やっぱあたしは", "04.20"));
        contact4.add(new ModelChat("こんなところじゃ終われない", "05.20"));

        ArrayList<ModelChat> contact5 = new ArrayList<>();
        contact5.add(new ModelChat("Words flow from right to left", "20.20"));
        contact5.add(new ModelChat("You stare at the screen looking bored", "21.20"));
        contact5.add(new ModelChat("I'll always come to meet you across the dimensional wall", "23.20"));
        contact5.add(new ModelChat("So get your heart ready", "01.20"));

        ArrayList<ModelChat> contact6 = new ArrayList<>();
        contact6.add(new ModelChat("The clock stopped ticking forever ago", "20.20"));
        contact6.add(new ModelChat("How long have I been up? I don't know.", "21.20"));
        contact6.add(new ModelChat("I can't get a grip, but I can't let go", "23.20"));
        contact6.add(new ModelChat("There wasn't anything to hold on to though", "01.20"));
        contact6.add(new ModelChat("What the hell's going on?!", "02.20"));

        ArrayList<ModelChat> contact7 = new ArrayList<>();
        contact7.add(new ModelChat("Lorem Ipsum Dolor Sit Amet", "20.20"));
        contact7.add(new ModelChat("Lorem Ipsum Dolor Sit Amet", "21.20"));

        ArrayList<ModelChat> contact8 = new ArrayList<>();
        contact8.add(new ModelChat("The clock stopped ticking forever ago", "20.20"));
        contact8.add(new ModelChat("How long have I been up? I don't know.", "21.20"));
        contact8.add(new ModelChat("I can't get a grip, but I can't let go", "23.20"));
        contact8.add(new ModelChat("There wasn't anything to hold on to though", "01.20"));
        contact8.add(new ModelChat("SO WHATTT", "02.20"));

        ArrayList<ModelChat> contact9 = new ArrayList<>();
        contact9.add(new ModelChat("I'm dead inside", "20.20"));

        conversation.add(contact1);
        conversation.add(contact2);
        conversation.add(contact3);
        conversation.add(contact4);
        conversation.add(contact5);
        conversation.add(contact6);
        conversation.add(contact7);
        conversation.add(contact8);
        conversation.add(contact9);

        return conversation;
    }

}
