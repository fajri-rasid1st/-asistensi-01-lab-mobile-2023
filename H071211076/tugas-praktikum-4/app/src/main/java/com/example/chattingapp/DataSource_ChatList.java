package com.example.chattingapp;

import java.util.ArrayList;
import java.util.Arrays;

public class DataSource_ChatList {
    public static ArrayList<ChatList> chatlist = new ArrayList<>(
            Arrays.asList(
                    new ChatList("هناك حقيقة مثبتة منذ زمن طويل وهي أن المحتوى المقروء لصفحة ما سيلهي القارئ عن التركيز على الشكل الخارجي للنص أو شكل توضع الفقرات في الصفحة التي يقرأها. ولذلك يتم استخدام طريقة لوريم إيبسوم لأنها تعطي توزيعاَ طبيعياَ -إلى حد ما- للأحرف عوضاً عن استخدام \"هنا يوجد محتوى نصي، هنا يوجد محتوى نصي\"","20.20"),
                    new ChatList("Lorem ipsum may be used as a placeholder before final copy is available.","20.20"),
                    new ChatList("Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document or a typeface without relying on meaningful content.","20.20")

            )
    );
    static ArrayList<ChatList> getChatList2() {
        ArrayList<ChatList> cl = chatlist;
        return cl;
    }
}
