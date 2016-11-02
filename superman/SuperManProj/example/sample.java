    public static Document createSample() {
        ProvmanFactory provFactory = new ProvmanFactory();
        Collection<Entity> entities = new ArrayList<Entity>();
        Collection<Agent> agents = new ArrayList<Agent>();
        Collection<Activity> activities = new ArrayList<Activity>();
        Collection<Relation> relations = new ArrayList<Relation>();
        // Entities
        Entity e2 = provFactory.newEntity("dataSet1");
        Entity e3 = provFactory.newEntity("regionList1");
        Entity e4 = provFactory.newEntity("composition1");
        Entity e5 = provFactory.newEntity("chart1");
        entities.add(e2);
        entities.add(e3);
        entities.add(e4);
        entities.add(e5);
        // Activities
        Activity act1 = provFactory.newActivity("compose1", "Status", "Done");
        Activity act2 = provFactory.newActivity("illustrate1", "Status", "Ongoing");
        Activity act3 = provFactory.newActivity("compile1", "Status", "Planned");
        activities.add(act1);
        activities.add(act2);
        activities.add(act3);
        // Agents
        Agent agent1 = provFactory.newAgent("derek", "type", "Person");
        provFactory.addAgentAttributes(agent1, "givenName", "Derek");
        provFactory.addAgentAttributes(agent1, "mbox", "derek@example.org");
        agents.add(agent1);
        Agent agent2 = provFactory.newAgent("chartgen", "type", "Organization");
        provFactory.addAgentAttributes(agent2, "name", "Chart Gen. Inc");
        agents.add(agent2);
        // Relationships
        wasAssociatedWith waw1 = provFactory.newWasAssociatedWith("waw1", act2, agent1, e2, "role", "analyst");
        relations.add(waw1);
        actedOnBehalfOf abo1 = provFactory.newActedOnBehalfOf("abo1", agent1, agent2);
        relations.add(abo1);
        wasAttributedTo wat1 = provFactory.newWasAttributedTo("wat1", e5, agent1);
        relations.add(wat1);
        wasInformedBy wib = new wasInformedBy();
        wib.setInformed(act1);	wib.setInformant(act2);   relations.add(wib);
        used used1 = provFactory.newUsed("used1", act1, e2, "role", "dataToCompose");
        relations.add(used1);
        used used2 = provFactory.newUsed("used2", act1, e3, "role", "regionsToAggregateBy");
        relations.add(used2);
        wasGeneratedBy wgb1 = provFactory.newWasGeneratedBy("wgb1", e4, act1, "role", "composedData");
        relations.add(wgb1);
        used used3 = provFactory.newUsed("used3", act2, e4);
        relations.add(used3);
        wasGeneratedBy wgb2 = provFactory.newWasGeneratedBy("wgb2", e5, act2);
        relations.add(wgb2);
        Entity e6 = provFactory.newEntity("chart2");
        entities.add(e6);
        used used4 = provFactory.newUsed("used4", act3, e5);
        relations.add(used4);
        wasGeneratedBy wgb3 = provFactory.newWasGeneratedBy("wgb3", e6, act3);
        relations.add(wgb3);
        wasDerivedFrom wdf1 = provFactory.newWasDerivedFrom("wdf1", e5, e4, "type", "Revision");
        relations.add(wdf1);
        alternateOf alt = provFactory.newAlternateOf("alt", e6, e5, "type", "Revision");
        relations.add(alt);

        Document document = provFactory.newProvGraph("provenance of an online newspaper article",
                activities, entities, agents, relations);

        return document;
    }
