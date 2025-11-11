package com.blogApplication.Blog.impls;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.blogApplication.Blog.dtos.PostResponse;
import com.blogApplication.Blog.exceptions.CategoryNotFoundException;
import com.blogApplication.Blog.exceptions.PostNotFoundException;
import com.blogApplication.Blog.exceptions.UserNotFoundException;
import com.blogApplication.Blog.models.Category;
import com.blogApplication.Blog.models.Post;
import com.blogApplication.Blog.models.User;
import com.blogApplication.Blog.repos.CategoryRepo;
import com.blogApplication.Blog.repos.PostRepo;
import com.blogApplication.Blog.repos.UserRepo;
import com.blogApplication.Blog.services.PostService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class PostImpl implements PostService {
    @Autowired
    private final PostRepo postRepo;
    @Autowired
    private final UserRepo userRepo;
    @Autowired
    private final CategoryRepo categoryRepo;

    @Transactional
    @Override
    public Post createPost(Post post, Integer userId, Integer categoryId) {
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("No user with this id"));
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("No category with this id"));
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        return postRepo.save(post);
    }

    @Override
    public Post updatePost(Post postDto, int postId) {
        Post post = getPostById(postId);
        if (post != null) {
            post.setPostTitle(postDto.getPostTitle());
            post.setPostContent(postDto.getPostContent());
            post.setImageName(postDto.getImageName());
            return postRepo.save(post);
        } else {
            throw new PostNotFoundException("No post with this id");

        }
    }

    @Override
    public void deletePost(int postId) {
        Post post = getPostById(postId);
        postRepo.delete(post);
    }

    @Override
    public Post getPostById(int postId) {
        return postRepo.findById(postId).orElseThrow(() -> new PostNotFoundException("No post with this id"));
    }

    @Override
    public List<Post> getPostsByCategory(int categoryId) {
        Category category = categoryRepo.findById(categoryId)
                .orElseThrow(() -> new CategoryNotFoundException("No category with this id"));
        return postRepo.findByCategory(category);

    }

    @Override
    public List<Post> getPostsByUser(int userId) {
        User user = userRepo.findById(userId).orElseThrow(() -> new UserNotFoundException("No user with this id"));
        return postRepo.findByUser(user);
    }

    @Override
    public List<Post> searchPosts(String keyword) {
        return postRepo.findByPostTitleContainingIgnoreCase(keyword);
    }

   // PostServiceImpl.java (getAllPost method)

@Override
@Transactional // Transaction ကို ဆက်လက်ထားရှိပါ
public PostResponse getAllPost(Integer pageNumber, Integer pageSize, String sortBy, String sortDir) {
    
   Sort sort = sortDir.equalsIgnoreCase("asc") ? 
                Sort.by(sortBy).ascending() : 
                Sort.by(sortBy).descending();

    Pageable p = PageRequest.of(pageNumber, pageSize, sort);
    
    // Repository မှာ JOIN FETCH သုံးထားတဲ့ method ကို ခေါ်ပါ
    Page<Post> pagePost = postRepo.findAllPostsWithComments(p); 
    
    List<Post> allPosts = pagePost.getContent();
    
    // DTO mapping မလုပ်ဘဲ Post Entity list ကို တိုက်ရိုက် PostResponse ထဲ ထည့်ပါ
    PostResponse postResponse = new PostResponse();
    postResponse.setContent(allPosts); 
    
    postResponse.setPageNumber(pagePost.getNumber());
    postResponse.setPageSize(pagePost.getSize());
    postResponse.setTotalElements(pagePost.getTotalElements());
    postResponse.setTotalPages(pagePost.getTotalPages()); // totalPages ကို ထည့်ရန်လိုပါမည်
    postResponse.setLastPage(pagePost.isLast());
    
    return postResponse;
}

}
