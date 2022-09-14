package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.Walk;
import dogTrio.arounDog.dto.WalkButtonDto;
import dogTrio.arounDog.dto.WalkDto;
import dogTrio.arounDog.repository.UserRepository;
import dogTrio.arounDog.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addWalk(String userId, WalkDto walkDto, String path) {
        Optional<User> user = userRepository.findByUserId(userId);
        walkDto.setUserAndPath(user.get(), path);
        walkRepository.save(walkDto);
    }

    @Transactional
    public List<Walk> getWalkListOrderedByGood(int start, int size) {
        return walkRepository.orderByGood(start, size);
    }

    @Transactional
    public void clickButton(WalkButtonDto walkButtonDto) {
        Walk findWalk = walkRepository.findOne(walkButtonDto.getWalkId());
        findWalk.clickButton(walkButtonDto.getButton());
    }
}
