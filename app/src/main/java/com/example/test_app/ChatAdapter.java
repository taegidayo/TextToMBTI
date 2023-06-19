package com.example.test_app;

import android.content.ClipData;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.List;

class Item {
    private String title;
    private boolean value;

    public Item(String title, boolean value) {
        this.title = title;
        this.value = value;
    }

    public String getTitle() {
        return title;
    }

    public boolean getValue() {
        return value;
    }
}
public class ChatAdapter extends BaseAdapter {
    private List<Item> items;

    public ChatAdapter(List<Item> items) {
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ChatMessageWidget chatMessageWidget;
        if (convertView == null) {
            chatMessageWidget = new ChatMessageWidget(parent.getContext());
        } else {
            chatMessageWidget = (ChatMessageWidget) convertView;
        }

        Item item = items.get(position);
        chatMessageWidget.setData(item.getTitle(), item.getValue());

        return chatMessageWidget;
    }
}
