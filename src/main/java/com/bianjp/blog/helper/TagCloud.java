package com.bianjp.blog.helper;

import com.bianjp.blog.entity.Tag;

import java.util.ArrayList;
import java.util.List;

public class TagCloud {
  private static float MAX_FONT_SIZE = 3;

  private List<Tag> tags;
  private int minPostCount;
  private int maxPostCount;
  private List<TagCloudItem> items;

  public TagCloud(List<Tag> tags) {
    this.tags = tags;
    construct();
  }

  public List<TagCloudItem> getItems() {
    return items;
  }

  private void construct() {
    if (tags == null || tags.isEmpty()) {
      items = new ArrayList<>();
      return;
    }

    minPostCount = tags.stream().mapToInt(Tag::getPostCount).min().getAsInt();
    maxPostCount = tags.stream().mapToInt(Tag::getPostCount).max().getAsInt();

    items = new ArrayList<>(tags.size());
    for (Tag tag : tags) {

      items.add(new TagCloudItem(tag, getFontSize(tag.getPostCount())));
    }
  }

  private float getFontSize(int count) {
    if (count == minPostCount) {
      return 1;
    } else {
      return MAX_FONT_SIZE * (count - minPostCount) / (maxPostCount - minPostCount);
    }
  }

  public static class TagCloudItem {
    private Tag tag;
    private float fontSize;

    TagCloudItem(Tag tag, float fontSize) {
      this.tag = tag;
      this.fontSize = fontSize;
    }

    public Tag getTag() {
      return tag;
    }

    public void setTag(Tag tag) {
      this.tag = tag;
    }

    public float getFontSize() {
      return fontSize;
    }

    public void setFontSize(float fontSize) {
      this.fontSize = fontSize;
    }
  }
}
