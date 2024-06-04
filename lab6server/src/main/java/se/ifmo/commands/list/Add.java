package se.ifmo.commands.list;

import se.ifmo.collection.Resource;
import se.ifmo.collection.format.CollectionManager;
import se.ifmo.collection.format.FileManager;
import se.ifmo.collection.models.StudyGroup;
import se.ifmo.commands.Command;
import se.ifmo.transfer.Request;
import se.ifmo.transfer.Response;

import java.util.Optional;

public class Add extends Command
{
    public Add() {
        super("add", "{element} - add new element to the collection", 1);
    }

    @Override
    public Response execute(Request request) {
        if (!request.getText().isEmpty()) System.out.println("Command add don't have to have any arguments");


        Optional<StudyGroup> toAddOptional = request.getResource().stream().findAny();

        if (toAddOptional.isEmpty()) {
            return new Response("Element for adding is absent.");
        }

        StudyGroup groupToAdd = toAddOptional.get();

        return new Response(CollectionManager.getInstance().getResource().add(groupToAdd) ?
                "Element has been added successfully" : "Error occurred during adding of element");
    }
}
