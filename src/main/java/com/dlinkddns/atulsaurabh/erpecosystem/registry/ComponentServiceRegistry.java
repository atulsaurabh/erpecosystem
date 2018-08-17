package com.dlinkddns.atulsaurabh.erpecosystem.registry;

import com.dlinkddns.atulsaurabh.erpecosystem.plugin.Pluggable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Suyojan
 */
public final class ComponentServiceRegistry 
{
   private static ComponentServiceRegistry serviceRegistry=new ComponentServiceRegistry();
   private Map<String,Pluggable> plugins = new HashMap<>();
   
   private ComponentServiceRegistry(){
       
   }
   
   public  static ComponentServiceRegistry getInstance()
   {
       return serviceRegistry;
   }
   
   public void register(Pluggable pluggable)
   {
      plugins.put(pluggable.pluginName(), pluggable);
   }
   
   public void deregister(String pluggableKey)
   {
     plugins.remove(pluggableKey);
   }
}
