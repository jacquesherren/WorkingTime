package com.example.Samuel.myapplication.backend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;
import com.google.api.server.spi.response.CollectionResponse;
import com.google.api.server.spi.response.NotFoundException;
import com.google.appengine.api.datastore.Cursor;
import com.google.appengine.api.datastore.QueryResultIterator;
import com.googlecode.objectify.ObjectifyService;
import com.googlecode.objectify.cmd.Query;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Nullable;
import javax.inject.Named;

import static com.googlecode.objectify.ObjectifyService.ofy;

/**
 * WARNING: This generated code is intended as a sample or starting point for using a
 * Google Cloud Endpoints RESTful API with an Objectify entity. It provides no data access
 * restrictions and no data validation.
 * <p>
 * DO NOT deploy this code unchanged as part of a real application to real users.
 */
@Api(
        name = "timeApi",
        version = "v1",
        resource = "time",
        namespace = @ApiNamespace(
                ownerDomain = "backend.myapplication.Samuel.example.com",
                ownerName = "backend.myapplication.Samuel.example.com",
                packagePath = ""
        )
)
public class TimeEndpoint {

    private static final Logger logger = Logger.getLogger(TimeEndpoint.class.getName());

    private static final int DEFAULT_LIST_LIMIT = 20;

    static {
        // Typically you would register this inside an OfyServive wrapper. See: https://code.google.com/p/objectify-appengine/wiki/BestPractices
        ObjectifyService.register(Time.class);
    }

    /**
     * Returns the {@link Time} with the corresponding ID.
     *
     * @param _id the ID of the entity to be retrieved
     * @return the entity with the corresponding ID
     * @throws NotFoundException if there is no {@code Time} with the provided ID.
     */
    @ApiMethod(
            name = "get",
            path = "time/{_id}",
            httpMethod = ApiMethod.HttpMethod.GET)
    public Time get(@Named("_id") int _id) throws NotFoundException {
        logger.info("Getting Time with ID: " + _id);
        Time time = ofy().load().type(Time.class).id(_id).now();
        if (time == null) {
            throw new NotFoundException("Could not find Time with ID: " + _id);
        }
        return time;
    }

    /**
     * Inserts a new {@code Time}.
     */
    @ApiMethod(
            name = "insert",
            path = "time",
            httpMethod = ApiMethod.HttpMethod.POST)
    public Time insert(Time time) {
        // Typically in a RESTful API a POST does not have a known ID (assuming the ID is used in the resource path).
        // You should validate that time._id has not been set. If the ID type is not supported by the
        // Objectify ID generator, e.g. long or String, then you should generate the unique ID yourself prior to saving.
        //
        // If your client provides the ID then you should probably use PUT instead.
        ofy().save().entity(time).now();
        logger.info("Created Time with ID: " + time.get_id());

        return ofy().load().entity(time).now();
    }

    /**
     * Updates an existing {@code Time}.
     *
     * @param _id  the ID of the entity to be updated
     * @param time the desired state of the entity
     * @return the updated version of the entity
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Time}
     */
    @ApiMethod(
            name = "update",
            path = "time/{_id}",
            httpMethod = ApiMethod.HttpMethod.PUT)
    public Time update(@Named("_id") int _id, Time time) throws NotFoundException {
        // TODO: You should validate your ID parameter against your resource's ID here.
        checkExists(_id);
        ofy().save().entity(time).now();
        logger.info("Updated Time: " + time);
        return ofy().load().entity(time).now();
    }

    /**
     * Deletes the specified {@code Time}.
     *
     * @param _id the ID of the entity to delete
     * @throws NotFoundException if the {@code _id} does not correspond to an existing
     *                           {@code Time}
     */
    @ApiMethod(
            name = "remove",
            path = "time/{_id}",
            httpMethod = ApiMethod.HttpMethod.DELETE)
    public void remove(@Named("_id") int _id) throws NotFoundException {
        checkExists(_id);
        ofy().delete().type(Time.class).id(_id).now();
        logger.info("Deleted Time with ID: " + _id);
    }

    /**
     * List all entities.
     *
     * @param cursor used for pagination to determine which page to return
     * @param limit  the maximum number of entries to return
     * @return a response that encapsulates the result list and the next page token/cursor
     */
    @ApiMethod(
            name = "list",
            path = "time",
            httpMethod = ApiMethod.HttpMethod.GET)
    public CollectionResponse<Time> list(@Nullable @Named("cursor") String cursor, @Nullable @Named("limit") Integer limit) {
        limit = limit == null ? DEFAULT_LIST_LIMIT : limit;
        Query<Time> query = ofy().load().type(Time.class).limit(limit);
        if (cursor != null) {
            query = query.startAt(Cursor.fromWebSafeString(cursor));
        }
        QueryResultIterator<Time> queryIterator = query.iterator();
        List<Time> timeList = new ArrayList<Time>(limit);
        while (queryIterator.hasNext()) {
            timeList.add(queryIterator.next());
        }
        return CollectionResponse.<Time>builder().setItems(timeList).setNextPageToken(queryIterator.getCursor().toWebSafeString()).build();
    }

    private void checkExists(int _id) throws NotFoundException {
        try {
            ofy().load().type(Time.class).id(_id).safe();
        } catch (com.googlecode.objectify.NotFoundException e) {
            throw new NotFoundException("Could not find Time with ID: " + _id);
        }
    }
}