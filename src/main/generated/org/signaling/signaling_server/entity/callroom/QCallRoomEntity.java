package org.signaling.signaling_server.entity.callroom;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QCallRoomEntity is a Querydsl query type for CallRoomEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCallRoomEntity extends EntityPathBase<CallRoomEntity> {

    private static final long serialVersionUID = 293044878L;

    public static final QCallRoomEntity callRoomEntity = new QCallRoomEntity("callRoomEntity");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath roomName = createString("roomName");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QCallRoomEntity(String variable) {
        super(CallRoomEntity.class, forVariable(variable));
    }

    public QCallRoomEntity(Path<? extends CallRoomEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCallRoomEntity(PathMetadata metadata) {
        super(CallRoomEntity.class, metadata);
    }

}

