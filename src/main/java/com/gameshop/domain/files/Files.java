package com.gameshop.domain.files;

import com.gameshop.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Files extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String origin_filename;

    @Column
    private String save_filename;

    @Column
    private String owner;

    @Builder
    public Files(String origin_filename, String save_filename, String owner) {
        this.origin_filename = origin_filename;
        this.save_filename = save_filename;
        this.owner = owner;
    }

    public void update(String origin_filename, String save_filename) {
        this.origin_filename = origin_filename;
        this.save_filename = save_filename;
    }

}
