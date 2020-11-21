package org.drombler.mediacenter.business.impl;

import org.drombler.commons.spring.transaction.stereotype.TransactionalService;
import org.drombler.mediacenter.business.PhotoService;

/**
 * @author Florian
 */

@TransactionalService
public class PhotoServiceImpl implements PhotoService {

//    @Autowired
//    private EventRepository eventRepository;
//
//    public List<Event> getEventsByOwnersContaining(DromblerId owner) {
//        EventConverter eventConverter = new EventConverter();
//        return eventRepository.findAllByOwnersContaining(owner).stream()
//                .map(eventConverter::convertEvent)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public Event createEvent(DromblerUserId dromblerUserId, Event event) {
//        EventEntityConverter eventEntityConverter = new EventEntityConverter();
//        EventEntity eventEntity = eventEntityConverter.convertEvent(event);
//
//        EventEntity savedEventEntity = eventRepository.save(eventEntity);
//
//        EventConverter eventConverter = new EventConverter();
//        return eventConverter.convertEvent(savedEventEntity);
//    }
}
