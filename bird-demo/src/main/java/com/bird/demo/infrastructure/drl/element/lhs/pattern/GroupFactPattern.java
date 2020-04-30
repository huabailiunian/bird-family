package com.bird.demo.infrastructure.drl.element.lhs.pattern;

import com.bird.demo.infrastructure.drl.element.lhs.HasGroup;
import com.bird.demo.infrastructure.drl.enums.GroupType;

import java.util.List;

/**
 * @author youly
 * 2019/8/2 14:13
 */
public class GroupFactPattern extends SingleFactPattern implements HasGroup {

    private GroupType groupType;

    private List<GroupFactPattern> groupFactPatterns;

    public GroupFactPattern() {
    }

    public GroupFactPattern(GroupType groupType) {
        this.groupType = groupType;
        this.setGroup(true);
    }

    public GroupFactPattern(String factType, String bindName) {
        super(factType, bindName);
    }

    public void setGroupType(GroupType groupType) {
        this.groupType = groupType;
    }

    public List<GroupFactPattern> getGroupFactPatterns() {
        return groupFactPatterns;
    }

    public void setGroupFactPatterns(List<GroupFactPattern> groupFactPatterns) {
        this.groupFactPatterns = groupFactPatterns;
    }

    @Override
    public GroupType getGroupType() {
        return this.groupType;
    }
}
