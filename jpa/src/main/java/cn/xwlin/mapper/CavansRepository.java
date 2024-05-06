package cn.xwlin.mapper;

import cn.xwlin.entity.Cavans;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author xiang.liao
 * @create 2023/8/31
 */
@Repository
public interface CavansRepository extends JpaRepository<Cavans, Integer> {
}
