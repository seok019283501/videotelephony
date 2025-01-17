package org.signaling.signaling_server.entity.friend;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFriendEntity is a Querydsl query type for FriendEntity
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFriendEntity extends EntityPathBase<FriendEntity> {

    private static final long serialVersionUID = -1700154738L;

    public static final QFriendEntity friendEntity = new QFriendEntity("friendEntity");

    public final NumberPath<Long> fromMemberId = createNumber("fromMemberId", Long.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final EnumPath<org.signaling.signaling_server.entity.friend.enums.FriendStatus> status = createEnum("status", org.signaling.signaling_server.entity.friend.enums.FriendStatus.class);

    public final NumberPath<Long> toMemberId = createNumber("toMemberId", Long.class);

    public QFriendEntity(String variable) {
        super(FriendEntity.class, forVariable(variable));
    }

    public QFriendEntity(Path<? extends FriendEntity> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFriendEntity(PathMetadata metadata) {
        super(FriendEntity.class, metadata);
    }

}

