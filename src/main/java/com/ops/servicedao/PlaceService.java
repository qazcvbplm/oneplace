package com.ops.servicedao;

import com.ops.entity.Place;
import com.ops.entity.Use;

import java.util.List;

public interface PlaceService {

    List<Place> find(Place place);

    List<Place> findByCon(String userId);

    void updateName(Place place);

    Place findById(String id);

    void bz(String[] ids, int[] count, Use use);

    void bzupdate(Use use);

    List<Use> findbz(Use use);

    List<Place> findByUserId(String openid);

    int update(Place place);

}
