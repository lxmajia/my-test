package cn.xwlin.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

/**
 * @author xiang.liao
 * @create 2023/8/31
 */
@Getter
@Setter
@Entity(name = "cavans")
public class Cavans {
  @Id
  private Integer id;
  @Column(name = "cavans_name")
  private String cavansName;

  @ManyToMany
  @JoinTable(name = "ship",
          joinColumns = @JoinColumn(name = "cavans_id",referencedColumnName = "id"),
          inverseJoinColumns = @JoinColumn(name = "dict_id",  referencedColumnName = "id"))
  private List<Dict> dictList;
}
