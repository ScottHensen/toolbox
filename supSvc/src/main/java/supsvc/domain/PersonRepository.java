package supsvc.domain;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


//NOTES:
// - Just use straight-out-of-the-box Paging and Sorting Repo; 
@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends PagingAndSortingRepository<Person, Long> 
//public interface PersonRepository extends Repository<Person, Long> 
{
	List<Person> findByLastName(@Param("name") String name);
}
