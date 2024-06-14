package project.financement.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;
import project.financement.generator.UuidTimeSequenceGenerator;

import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Table(name = "roles")
@EqualsAndHashCode(exclude = {"authoritySet", "userInfos"})
@NoArgsConstructor
public class Role {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", type = UuidTimeSequenceGenerator.class)
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "role_name")
    private String roleName;


    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    private Set<Authority> authoritySet;

    @OneToMany(mappedBy = "role")
    private Set<UserInfo> userInfos;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
