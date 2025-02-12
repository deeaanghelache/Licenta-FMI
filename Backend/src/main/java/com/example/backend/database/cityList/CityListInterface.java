package com.example.backend.database.cityList;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CityListInterface extends JpaRepository<CityList, Integer> {
    Optional<CityList> findCityListByCityListId(Integer cityListId);

    @Query("SELECT cl FROM CityList cl JOIN cl.user u WHERE u.userId = :userId AND cl.city.cityId = :cityId")
    List<CityList> getCityListByUserAndCity(@Param("userId") Integer userId, @Param("cityId") Integer cityId);

    @Query(value = "SELECT city_list.city_list_id, city_list.total_price, city_list.user_id, city_list.city_id " +
            "FROM city_list JOIN user ON (city_list.user_id = user.user_id) " +
            "WHERE city_list.user_id = :userId", nativeQuery = true)
    List<CityList> queryBy(@Param("userId") Integer userId);

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM city_list WHERE city_id = :cityId AND user_id = :userId", nativeQuery = true)
    void queryBy(@Param("cityId") Integer cityId, @Param("userId") Integer userId);

    void deleteCityListByCityListId(Integer cityListId);
}
