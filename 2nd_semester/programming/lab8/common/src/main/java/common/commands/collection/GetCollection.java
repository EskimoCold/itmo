package common.commands.collection;

import common.network.Response;

public class GetCollection extends CollectionCommand{
    @Override
    public String getName() {
        return "get_collection";
    }

    @Override
    public String getDescription() {
        return "";
    }

    @Override
    public Response execute(String[] args) {
        return new Response(null, this.getCollectionHandler().getCollection());
    }
}
