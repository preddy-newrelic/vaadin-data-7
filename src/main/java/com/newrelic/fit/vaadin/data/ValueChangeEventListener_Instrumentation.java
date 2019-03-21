package com.newrelic.fit.vaadin.data;

import java.util.logging.Level;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.vaadin.data.Property;
import com.vaadin.server.Page;

@Weave(originalName="com.vaadin.data.Property$ValueChangeListener", type=MatchType.Interface)
public abstract class ValueChangeEventListener_Instrumentation {
	
  @Trace
  public void valueChange(Property.ValueChangeEvent event) {
	  String name = event.getProperty().getValue().toString();
	  System.out.println("Custom Instrumentation - valuechanged event name is " + name);
	  NewRelic.getAgent().getLogger().log(Level.INFO, "Custom Instrumentation - valuechanged event name is " + name);
	  //Page.getCurrent().getJavaScript().execute("var myInteraction = newrelic.interaction().setName('" + name + "').save();");
	  
	  //call Agent API to name transaction
	  Transaction txn = NewRelic.getAgent().getTransaction();
	  txn.setTransactionName(TransactionNamePriority.CUSTOM_HIGH, false, "valuechanged", "ValueChanged");
	  
	  Weaver.callOriginal();	
	  
	  //Page.getCurrent().getJavaScript().execute("myInteraction.end()");
  }

}
