package it.epicode.phronesis.presentationlayer.api;

import it.epicode.phronesis.businesslayer.dto.FollowResponseDTO;
import it.epicode.phronesis.businesslayer.dto.FollowResponsePrj;
import it.epicode.phronesis.businesslayer.services.interfaces.FollowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/follow")
public class FollowController {

    @Autowired
    FollowService followService;

    @GetMapping("followers/{followingId}")
    public ResponseEntity<List<FollowResponsePrj>> getFollower(@PathVariable Long followingId) {
        return new ResponseEntity<>(followService.getFollowers(followingId), HttpStatus.OK);
    }

    @GetMapping("following/{followerId}")
    public ResponseEntity<List<FollowResponsePrj>> getFollowing(@PathVariable Long followerId) {
        return new ResponseEntity<>(followService.getFollowing(followerId), HttpStatus.OK);
    }

    @GetMapping("/is-following")
    public boolean isFollowing(@RequestParam Long followerId, @RequestParam Long followingId) {
        return followService.isFollowing(followerId, followingId);
    }

    @PostMapping()
    public ResponseEntity<FollowResponseDTO> follow(@RequestParam Long followerId, @RequestParam Long followingId) {
        return new ResponseEntity<>(followService.followUser(followerId, followingId), HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<FollowResponseDTO> unfollow(@RequestParam Long followerId, @RequestParam Long followingId) {
        return new ResponseEntity<>(followService.unfollowUser(followerId, followingId), HttpStatus.OK);
    }

}
