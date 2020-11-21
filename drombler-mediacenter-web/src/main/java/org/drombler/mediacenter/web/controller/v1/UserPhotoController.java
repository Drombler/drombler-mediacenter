package org.drombler.mediacenter.web.controller.v1;

/**
 * @author Florian
 */


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.drombler.mediacenter.business.MediaService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.drombler.mediacenter.web.controller.RestControllerUtils.V1_PATH;


@Api(tags = {"UserEventController V1"})
@RestController("UserEventControllerV1")
@RequestMapping(path = V1_PATH + "/me/events")
@RequiredArgsConstructor
public class UserPhotoController {

    private final MediaService eventService;

//    private final DromblerIdentityProviderManager dromblerIdentityProviderManager;
//
//    @GetMapping
//    @ApiOperation("Gets all events.")
//    public List<Event> getEvents(@RequestHeader("X-Drombler-owner") String owner) // TODO: replace with security principl
//    {
//        DromblerUserId dromblerUserId = DromblerUserId.parseDromblerUserId(owner, dromblerIdentityProviderManager);
//        return eventService.getEventsByOwnersContaining(dromblerUserId);
//    }
//
//    @PostMapping
//    @ApiOperation("Creates an event.")
//    public Event createEvent(@RequestHeader("X-Drombler-owner") String owner, @RequestBody Event event) // TODO: replace with security principl
//    {
//        DromblerUserId dromblerUserId = DromblerUserId.parseDromblerUserId(owner, dromblerIdentityProviderManager);
//        return eventService.createEvent(dromblerUserId, event);
//    }


}