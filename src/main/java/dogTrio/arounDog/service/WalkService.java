package dogTrio.arounDog.service;

import dogTrio.arounDog.domain.User;
import dogTrio.arounDog.domain.Walk;
import dogTrio.arounDog.dto.WalkDto;
import dogTrio.arounDog.repository.UserRepository;
import dogTrio.arounDog.repository.WalkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WalkService {

    private final WalkRepository walkRepository;
    private final UserRepository userRepository;

    @Transactional
    public void addWalk(Long memberId, WalkDto walkDto, String path) {
        User user = userRepository.findOne(memberId);
        walkDto.setUserAndPath(user, path);
        walkRepository.save(walkDto);
    }
}
