package com.newrelic.fit.vaadin.data;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.vaadin.data.Property;

@Weave(originalName="com.vaadin.data.Property$ValueChangeListener", type=MatchType.Interface)
public abstract class ValueChangeEventListener_Instrumentation {
  public void valueChange(Property.ValueChangeEvent event) {
    //call Agent API to 
    NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_HIGH, false, "valuechanged", event.getProperty().getValue().toString());
    Weaver.callOriginal();
  }

}
