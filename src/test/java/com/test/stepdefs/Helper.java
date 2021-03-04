package com.test.stepdefs;


import io.cucumber.messages.internal.com.google.gson.JsonArray;
import io.cucumber.messages.internal.com.google.gson.JsonElement;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.cucumber.messages.internal.com.google.gson.JsonPrimitive;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Helper {
    
 //currently this class is not used 
    static Map<String, Object> createMapFromJsonObject(JsonObject jo)
  {
        Map<String, Object> map = new HashMap<String, Object>();
        for (Map.Entry<String, JsonElement> entry : jo.entrySet())
        {
          String key = entry.getKey();
          JsonElement value = entry.getValue();
          map.put(key, getValueFromJsonElement(value));
        }
        return map;
  }

static Object getValueFromJsonElement(JsonElement je)
{
  if (je.isJsonObject())
  {
    return createMapFromJsonObject(je.getAsJsonObject());
  }
  else if (je.isJsonArray())
  {
    JsonArray array = je.getAsJsonArray();
    List<Object> list = new ArrayList<Object>(array.size());
    for (JsonElement element : array)
    {
      list.add(getValueFromJsonElement(element));
    }
    return list;
  }
  else if (je.isJsonNull())
  {
    return null;
  }
  else // must be primitive
  {
    JsonPrimitive p = je.getAsJsonPrimitive();
    if (p.isBoolean()) return p.getAsBoolean();
    if (p.isString()) return p.getAsString();
    // else p is number, but don't know what kind
    String s = p.getAsString();
    try
    {
      return new BigInteger(s);
    }
    catch (NumberFormatException e)
    {
      // must be a decimal
      return new BigDecimal(s);
    }
  }
}

}
